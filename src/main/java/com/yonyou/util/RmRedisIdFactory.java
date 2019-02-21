package com.yonyou.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.nosql.redis.JedisTemplate;

import com.yonyou.iuap.utils.PropertyUtil;
import com.yonyou.util.jdbc.BaseDao;
import com.yonyou.util.jdbc.IBaseDao;

/**
 * 
* @ClassName: RmIdFactory 
* @Description: 基于redis  ID生成器单例 
* @author 博超
* @date 2016年12月27日 
*
 */
public class RmRedisIdFactory implements IRmIdFactory { 
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 批查询的数量
	 */
	private static int MAX_BATCH_SIZE = Integer.parseInt(PropertyUtil.getPropertyByKey("MAX_BATCH_SIZE"));
    
	/**
	 * 缓存redis的Key的前缀字符串，后接数据库表的名称
	 */
	private static String  REDIS_ID_PREFIX="RM_ID_"; 
	
	@Autowired
	JedisTemplate jedisTemplate;
	

	/**
	 * ID后几位每天从1计数，判断当天的id是否已经取过，当天已经取过缓存本地
	 */
	private static ConcurrentHashMap<String, String> tableIDByDate= new ConcurrentHashMap<String, String>();
	
	/**
	 * 表名和前缀编码对应关系
	 */
	private static ConcurrentHashMap<String, String> table2TableCode= new ConcurrentHashMap<String, String>();
	
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void initBeanFactory() {
        try {
        	
        	logger.info("数据库ID初始化", "进入初始化方法！");
        	 
            long startTime = System.currentTimeMillis();
            //初始化单例对象
            
            //存放table_name -> <table table_code="2003" table_name="RM_AFFIX" id_name="ID"/>
            Map<String, Element> mTableName_Ele = new HashMap<String, Element>();
            //id.xml命名空间
            Map<String, String> defaultNameSpaceMap = new HashMap<String, String>();  
            defaultNameSpaceMap.put("q", "http://www.quickbundle.org/schema");
            //读入id.xml
           
            Document docId_xml = XMLUtils.parse("/id.xml",defaultNameSpaceMap);
            List<Element> lTable = docId_xml.selectNodes("/q:RmIdFactory/q:table");
            for(Element eleTable : lTable) {
            	String tableName = eleTable.valueOf("@table_name").toUpperCase();
            	mTableName_Ele.put(tableName, eleTable);
            	//this.mId.put(tableName, new AtomicLong());//放弃对mId初始化。
            }

            logger.info("数据库ID初始化", "获取id.xml！"+docId_xml.toString());
            doInitIdBatch(mTableName_Ele);
           
            logger.info("init " + mTableName_Ele.size() + " tables, cost " + (System.currentTimeMillis()-startTime) + " milliseconds!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("id.xml初始化失败:" + e.toString());
        }
    }
    
    
    //TODO 扩展为在多集群高并发下获得 从1开始auto_increment的id
    /**
     * 
    * @Title: getSqlSelectMax 
    * @Description: 根据<table table_code="2003" table_name="RM_AFFIX" id_name="ID"/>和clusterIdPrefix，获得查询最大值的sql 
    * @param eleTable
    * @param clusterIdPrefix
    * @return
     */
    String getSqlSelectMax(Element eleTable, String clusterIdPrefix) {
        String tableName = eleTable.valueOf("@table_name");
		String tablePrefix = clusterIdPrefix + eleTable.valueOf("@table_code")+DateUtil.getNowDateYYMMDD();
		String idName = eleTable.valueOf("@id_name");
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("max(to_number(");
		sql.append(idName);
		sql.append(")) ");
		sql.append("MAX_ID from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(idName);
		sql.append(" like '");
		sql.append(tablePrefix);
		sql.append("%' ");
		sql.append(" and "+idName+" >= '"+tablePrefix+"000000000'");
		sql.append(" and "+idName+" <= '"+tablePrefix+"999999999'");
    	return sql.toString();
    }
    
    /**
     * 
    * @Title: doInitIdBatch 
    * @Description: 批量初始化
    * @param mTableName_Ele
     */
    void doInitIdBatch(Map<String, Element> mTableName_Ele) {
        int batchSize = 0;
        Map<String, Element> todoTable = new HashMap<String, Element>();
        for (Map.Entry<String, Element> en: mTableName_Ele.entrySet()) {
            String tableName = en.getKey().toUpperCase();
        	Element eleTable = en.getValue();     
        	todoTable.put(tableName, eleTable);
        }
        doInitIdBatchQuery(todoTable);
    }
    
    /**
     * 
    * @Title: doInitIdBatchQuery 
    * @Description: 批量初始化查询
    * @param todoTable
     */
    void doInitIdBatchQuery(Map<String, Element> todoTable) {
    	if(todoTable.size() == 0) {
    		return;
    	}
    	if(null==jedisTemplate){
    		
    		logger.info("数据库ID初始化", "没有初始化获取Redis连接！");
    		logger.error("数据库ID初始化:没有初始化获取Redis连接！");
    		return ;
    	}
    	IBaseDao dcmsDao = BaseDao.getBaseDao();
        //取到当前的集群节点ID
       
        StringBuilder sql = new StringBuilder();
        int indexThisBatch = 0;
        List<String> lTmpTableName = new ArrayList<String>();
        List<String> lTmpTablePrefix = new ArrayList<String>();
    	for (Map.Entry<String, Element> en: todoTable.entrySet()) {
            String tableName = en.getKey();
        	Element eleTable = en.getValue();
        	
        	table2TableCode.put(tableName, eleTable.valueOf("@table_code"));
        	
        	String redisSeq=jedisTemplate.get(REDIS_ID_PREFIX+tableName+DateUtil.getNowDateYYMMDD());
        	if(null==redisSeq){
        		String tablePrefix =  eleTable.valueOf("@table_code")+DateUtil.getNowDateYYMMDD();
        		lTmpTableName.add(tableName);
        		lTmpTablePrefix.add(tablePrefix);
        		String sqlSingle = getSqlSelectMax(eleTable, "");
        		List<Map<String, Object>> lResult= dcmsDao.query(sqlSingle, "MAX_ID");
        		for(Iterator<Map<String, Object>> itLResult = lResult.iterator();itLResult.hasNext();){
    				Map<String, Object> result = itLResult.next();
    				String dbSeq=null;    				
    			    if(null==result.get("MAX_ID")){
    			    	dbSeq="0";    			    	
    			    }else{
    			    	dbSeq=result.get("MAX_ID").toString().substring(10);
    			    }
    			    jedisTemplate.set(REDIS_ID_PREFIX+tableName+DateUtil.getNowDateYYMMDD(), dbSeq);  
    			    logger.info("数据库ID初始化", "设置redis表id初始化值"+REDIS_ID_PREFIX+tableName+DateUtil.getNowDateYYMMDD()+dbSeq);
    			    
    			}
        		
        	}
        	
    	}
    	
    }
  
    /**
     * 
    * @Title: getIdFactory 
    * @Description: 获得单例
    * @return
     */
    public static IRmIdFactory getIdFactory() {
        if(!isInitId) {
            synchronized (RmRedisIdFactory.class) {
                if(!isInitId) {
                	idFactory = (IRmIdFactory) SpringContextUtil.getBean("IRmRedisIdFactory");
                	idFactory.initBeanFactory();
                    isInitId = true;
                }
            }
        }
        return idFactory;
    }

    public synchronized String[] requestIdInner(String tableName, int length) {
		
    	if(null==jedisTemplate){    		
    		 logger.info("数据库ID初始化", "没有获取Redis连接！");
	   		 logger.error("数据库ID初始化:没有获取Redis连接！");
	   		 return null;
   	    }
    	
        //redis缓存ID的计数
    	String redisSeq ="";
    	String prefix = tableName+DateUtil.getNowDateYYMMDD();
    	//判断当天服务器是否已经从新生成ID
    	if(tableIDByDate.containsKey(tableName)&&prefix.equals(tableIDByDate.get(tableName))){    
    		
    		redisSeq=jedisTemplate.incrBy(REDIS_ID_PREFIX+prefix,length).toString();   
    		
    	}else{
    		
    		String tempid= jedisTemplate.get(REDIS_ID_PREFIX+prefix);
    		if(null==tempid){
    			logger.info("数据库ID初始化", "redis没有获取到"+REDIS_ID_PREFIX+prefix+"前缀的ID，重新初始化！");

    			//服务器没有当天没有初始化，将所有表一次初始化；也可能是服务器redis宕机，Id从新从数据库初始化
    			initBeanFactory();
    			redisSeq=jedisTemplate.incrBy(REDIS_ID_PREFIX+prefix,length).toString();
    		}else{    			
    			//服务器已经初始化当天id，但本地没有记录，该结点第一次获取该表记录 
    			redisSeq=jedisTemplate.incrBy(REDIS_ID_PREFIX+prefix,length).toString();
    			tableIDByDate.put(tableName, prefix);
    		}
    	}
    	
    	//批量生成时的第一个id
    	int redisSeqInt=Integer.parseInt(redisSeq)-length+1;
    	
        String[] ids = new String[length];
        String prefixstr=table2TableCode.get(tableName)+DateUtil.getNowDateYYMMDD();
        //批量获取id
        for (int i = 0; i < ids.length; i++) {    
        	//根据数据库表编码+6位年月日+每天从1计数
        	ids[i]=prefixstr+String.format("%9d", redisSeqInt+i).replace(" ", "0");
		}
       
        return ids;
    }
    
    
    
    /**
     * 批量获取唯一ID
     * @param tableName 表名
     * @param length 批量数
     * @return 返回内存中自增长的ID，未找到返回null
     */
    public static String[] requestId(String tableName, int length) {
    	if(length < 1) {
    		return new String[0];
    	}
    	return getIdFactory().requestIdInner(tableName.toUpperCase(), length);
    }
    
	/**
	 * 全局单例
	 */
	private static IRmIdFactory idFactory = null;
	/**
	 * 全局单例的初始化标记，用于双检锁安全判断
	 */
	private static boolean isInitId = false;
}