package aTaxiAPI;


/**
 * Cache' Java Class Generated for class aTaxiAPI.TaximeterLast on version Cache for Windows (x86-64) 2010.2.2 (Build 600) Wed Dec 8 2010 16:37:18 EST.<br>
 *
 * @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast</A>
**/

public class TaximeterLast extends aTaxiAPI.JSON implements java.io.Serializable {
    private static final long serialVersionUID = 2163;
    private static String CACHE_CLASS_NAME = "aTaxiAPI.TaximeterLast";
    /**
           <p>NB: DO NOT USE IN APPLICATION(!!!).
           <br>Use <code>aTaxiAPI.TaximeterLast.open</code> instead!</br></p>
           <p>
           Used to construct a Java object, corresponding to existing object
           in Cache database.
           </p>
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
    */
    public TaximeterLast (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    public TaximeterLast (com.intersys.objects.Database db, String initstr) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME,initstr));
    }
    /**
       Creates a new instance of object "aTaxiAPI.TaximeterLast" in Cache
       database and corresponding object of class
       <code>aTaxiAPI.TaximeterLast</code>.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @throws com.intersys.objects.CacheException in case of error.

              @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
              @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
     */
    public TaximeterLast (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    /**
       Returns class name of the class aTaxiAPI.TaximeterLast as it is in
      Cache Database. Note, that this is a static method, so no
      object specific information can be returned. Use
      <code>getCacheClass().getName()</code> to get the class name
      for specific object.
       @return Cache class name as a <code>String</code>
      @see #getCacheClass()
      @see com.intersys.objects.reflect.CacheClass#getName()
     */
    public static String getCacheClassName( ) {
        return CACHE_CLASS_NAME;
    }

   /**
           Allows access metadata information about type of this object
           in Cache database. Also can be used for dynamic binding (accessing
           properties and calling methods without particular class known).

           @return <code>CacheClass</code> object for this object type.
   */
    public com.intersys.objects.reflect.CacheClass getCacheClass( ) throws com.intersys.objects.CacheException {
        return mInternal.getCacheClass();
    }

    public static void checkAllFieldsValid(com.intersys.objects.Database db ) throws com.intersys.objects.CacheException {
    }

    /**
       Verifies that all fields from Cache class are exposed with
       accessor methods in Java class and that values for indexes in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p>But if there is any inconsistency in zObjVal indexes this is fatal and class can not work correctly and must be regenerated

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws com.intersys.objects.InvalidClassException if any inconsistency is found.
       @throws com.intersys.objects.CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see com.intersys.objects.InvalidPropertyException

     */
    public static void checkAllMethods(com.intersys.objects.Database db ) throws com.intersys.objects.CacheException {
        checkAllMethods(db, CACHE_CLASS_NAME, TaximeterLast.class);
    }
    /**
     <p>Runs method %ClassName in Cache.</p>
     <p>Description: Returns the object's class name. The <var>fullname</var> determines how the
class name is represented. If it is 1 then it returns the full class name
including any package qualifier. If it is 0 (the default) then it returns the
name of the class without the package, this is mainly for backward compatibility
with the pre-package behaviour of %ClassName.</p>
     @param db represented as com.intersys.objects.Database
     @param fullname represented as java.lang.Boolean
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#%ClassName"> Method %ClassName</A>
    */
    public static java.lang.String sys_ClassName (com.intersys.objects.Database db, java.lang.Boolean fullname) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(fullname);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%ClassName",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method %IsA in Cache.</p>
     <p>Description: Returns true (1) if instances of this class are also instances of the isclass parameter.
That is 'isclass' is a primary superclass of this object.</p>
     @param db represented as com.intersys.objects.Database
     @param isclass represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#%IsA"> Method %IsA</A>
    */
    public static java.lang.Integer sys_IsA (com.intersys.objects.Database db, java.lang.String isclass) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(isclass);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%IsA",args,com.intersys.objects.Database.RET_PRIM);
        return res.getInteger();
    }
    /**
     <p>Runs method AddString in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inString represented as java.lang.String
     @param inAddString represented as java.lang.String
     default argument inSeparator set to ","
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #AddString(com.intersys.objects.Database,java.lang.String,java.lang.String,java.lang.String)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#AddString"> Method AddString</A>
    */
    public static java.lang.String AddString (com.intersys.objects.Database db, java.lang.String inString, java.lang.String inAddString) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inString);
        args[1] = new com.intersys.cache.Dataholder(inAddString);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"AddString",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method AddString in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inString represented as java.lang.String
     @param inAddString represented as java.lang.String
     @param inSeparator represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#AddString"> Method AddString</A>
    */
    public static java.lang.String AddString (com.intersys.objects.Database db, java.lang.String inString, java.lang.String inAddString, java.lang.String inSeparator) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inString);
        args[1] = new com.intersys.cache.Dataholder(inAddString);
        args[2] = new com.intersys.cache.Dataholder(inSeparator);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"AddString",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Answer in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param GUID represented as java.lang.String
     default argument inCode set to ""
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #Answer(com.intersys.objects.Database,java.lang.String,java.lang.String)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#Answer"> Method Answer</A>
    */
    public static void Answer (com.intersys.objects.Database db, java.lang.String GUID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(GUID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Answer",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method Answer in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param GUID represented as java.lang.String
     @param inCode represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#Answer"> Method Answer</A>
    */
    public static void Answer (com.intersys.objects.Database db, java.lang.String GUID, java.lang.String inCode) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(GUID);
        args[1] = new com.intersys.cache.Dataholder(inCode);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Answer",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method DriverOffLine in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#DriverOffLine"> Method DriverOffLine</A>
    */
    public static java.lang.String DriverOffLine (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverOffLine",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method DriverOnLine in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#DriverOnLine"> Method DriverOnLine</A>
    */
    public static java.lang.String DriverOnLine (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverOnLine",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method FromUTF in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inText represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#FromUTF"> Method FromUTF</A>
    */
    public static java.lang.String FromUTF (com.intersys.objects.Database db, java.lang.String inText) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inText);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"FromUTF",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GUID in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GUID"> Method GUID</A>
    */
    public static java.lang.String GUID (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[0];
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GUID",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GenerationSQL in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     default argument isLower set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #GenerationSQL(com.intersys.objects.Database,java.lang.String,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GenerationSQL"> Method GenerationSQL</A>
    */
    public static java.lang.String GenerationSQL (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GenerationSQL",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GenerationSQL in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     @param isLower represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GenerationSQL"> Method GenerationSQL</A>
    */
    public static java.lang.String GenerationSQL (com.intersys.objects.Database db, java.lang.String inSQL, java.lang.Integer isLower) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        args[1] = new com.intersys.cache.Dataholder(isLower);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GenerationSQL",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GenerationSQLArray in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GenerationSQLArray"> Method GenerationSQLArray</A>
    */
    public static java.lang.String GenerationSQLArray (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GenerationSQLArray",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GenerationSQLSingleField in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GenerationSQLSingleField"> Method GenerationSQLSingleField</A>
    */
    public static java.lang.String GenerationSQLSingleField (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GenerationSQLSingleField",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetAccount in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetAccount"> Method GetAccount</A>
    */
    public static java.lang.String GetAccount (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetAccount",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetData in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetData"> Method GetData</A>
    */
    public static java.lang.String GetData (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetData",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetDataEx in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetDataEx"> Method GetDataEx</A>
    */
    public static java.lang.String GetDataEx (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetDataEx",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetHisMessages in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     default argument inLastID set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #GetHisMessages(com.intersys.objects.Database,java.lang.Integer,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetHisMessages"> Method GetHisMessages</A>
    */
    public static java.lang.String GetHisMessages (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetHisMessages",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetHisMessages in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inLastID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetHisMessages"> Method GetHisMessages</A>
    */
    public static java.lang.String GetHisMessages (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inLastID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inLastID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetHisMessages",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetHisOrders in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     default argument inLastID set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #GetHisOrders(com.intersys.objects.Database,java.lang.Integer,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetHisOrders"> Method GetHisOrders</A>
    */
    public static java.lang.String GetHisOrders (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetHisOrders",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetHisOrders in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inLastID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetHisOrders"> Method GetHisOrders</A>
    */
    public static java.lang.String GetHisOrders (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inLastID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inLastID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetHisOrders",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetMessages in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetMessages"> Method GetMessages</A>
    */
    public static java.lang.String GetMessages (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetMessages",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetOrderJSON in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inOrderID represented as java.lang.Integer
     @param inDriverID represented as java.lang.Integer
     @param inDistance represented as java.lang.Double
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetOrderJSON"> Method GetOrderJSON</A>
    */
    public static java.lang.String GetOrderJSON (com.intersys.objects.Database db, java.lang.Integer inOrderID, java.lang.Integer inDriverID, java.lang.Double inDistance) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inOrderID);
        args[1] = new com.intersys.cache.Dataholder(inDriverID);
        args[2] = new com.intersys.cache.Dataholder(inDistance);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetOrderJSON",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetOrders in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     default argument inIsPrior set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #GetOrders(com.intersys.objects.Database,java.lang.Integer,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetOrders"> Method GetOrders</A>
    */
    public static java.lang.String GetOrders (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetOrders",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetOrders in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inIsPrior represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetOrders"> Method GetOrders</A>
    */
    public static java.lang.String GetOrders (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inIsPrior) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inIsPrior);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetOrders",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetOrdersComplite in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#GetOrdersComplite"> Method GetOrdersComplite</A>
    */
    public static java.lang.String GetOrdersComplite (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetOrdersComplite",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method HisOrderGet in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inOrderID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#HisOrderGet"> Method HisOrderGet</A>
    */
    public static java.lang.String HisOrderGet (com.intersys.objects.Database db, java.lang.Integer inOrderID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inOrderID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"HisOrderGet",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Message in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inClassID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#Message"> Method Message</A>
    */
    public static java.lang.String Message (com.intersys.objects.Database db, java.lang.Integer inClassID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inClassID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Message",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method MessagesDeliver in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inMessageID represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#MessagesDeliver"> Method MessagesDeliver</A>
    */
    public static java.lang.String MessagesDeliver (com.intersys.objects.Database db, java.lang.String inMessageID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inMessageID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"MessagesDeliver",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method MessagesRead in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inMessageID represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#MessagesRead"> Method MessagesRead</A>
    */
    public static java.lang.String MessagesRead (com.intersys.objects.Database db, java.lang.String inMessageID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inMessageID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"MessagesRead",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method MessagesSend in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inText represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#MessagesSend"> Method MessagesSend</A>
    */
    public static java.lang.String MessagesSend (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.String inText) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inText);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"MessagesSend",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrdersApply in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#OrdersApply"> Method OrdersApply</A>
    */
    public static java.lang.String OrdersApply (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrdersApply",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrdersCheck in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inOrderID represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#OrdersCheck"> Method OrdersCheck</A>
    */
    public static java.lang.String OrdersCheck (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.String inOrderID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inOrderID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrdersCheck",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrdersDeny in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#OrdersDeny"> Method OrdersDeny</A>
    */
    public static java.lang.String OrdersDeny (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrdersDeny",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrdersDone in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#OrdersDone"> Method OrdersDone</A>
    */
    public static java.lang.String OrdersDone (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrdersDone",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrdersExecuted in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#OrdersExecuted"> Method OrdersExecuted</A>
    */
    public static java.lang.String OrdersExecuted (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrdersExecuted",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrdersWaiting in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#OrdersWaiting"> Method OrdersWaiting</A>
    */
    public static java.lang.String OrdersWaiting (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrdersWaiting",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method SQLQeurySet in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#SQLQeurySet"> Method SQLQeurySet</A>
    */
    public static void SQLQeurySet (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"SQLQeurySet",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method SetDriverAtClient in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#SetDriverAtClient"> Method SetDriverAtClient</A>
    */
    public static java.lang.String SetDriverAtClient (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"SetDriverAtClient",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method StatisticsOrders in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#StatisticsOrders"> Method StatisticsOrders</A>
    */
    public static java.lang.String StatisticsOrders (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"StatisticsOrders",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method StatisticsRating in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#StatisticsRating"> Method StatisticsRating</A>
    */
    public static java.lang.String StatisticsRating (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"StatisticsRating",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method StatisticsShare in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#StatisticsShare"> Method StatisticsShare</A>
    */
    public static java.lang.String StatisticsShare (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"StatisticsShare",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method UnlimitedTariffActivate in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inTariffID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#UnlimitedTariffActivate"> Method UnlimitedTariffActivate</A>
    */
    public static java.lang.String UnlimitedTariffActivate (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inTariffID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inTariffID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"UnlimitedTariffActivate",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method UnlimitedTariffGet in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#UnlimitedTariffGet"> Method UnlimitedTariffGet</A>
    */
    public static java.lang.String UnlimitedTariffGet (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"UnlimitedTariffGet",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method addField in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     default argument inReplace set to 1
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #addField(com.intersys.objects.Database,java.lang.String,java.lang.String,java.lang.String,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addField"> Method addField</A>
    */
    public static void addField (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inFieldName, java.lang.String inFieldValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        args[2] = new com.intersys.cache.Dataholder(inFieldValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addField",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method addField in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     @param inReplace represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addField"> Method addField</A>
    */
    public static void addField (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inFieldName, java.lang.String inFieldValue, java.lang.Integer inReplace) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[4];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        args[2] = new com.intersys.cache.Dataholder(inFieldValue);
        args[3] = new com.intersys.cache.Dataholder(inReplace);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addField",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method addFieldSQLArray in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inQuery represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addFieldSQLArray"> Method addFieldSQLArray</A>
    */
    public static void addFieldSQLArray (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inFieldName, java.lang.String inQuery) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        args[2] = new com.intersys.cache.Dataholder(inQuery);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addFieldSQLArray",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method addToArray in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     default argument inIsGUID set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #addToArray(com.intersys.objects.Database,java.lang.String,java.lang.String,java.lang.String,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addToArray"> Method addToArray</A>
    */
    public static void addToArray (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inFieldName, java.lang.String inFieldValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        args[2] = new com.intersys.cache.Dataholder(inFieldValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addToArray",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method addToArray in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     @param inIsGUID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addToArray"> Method addToArray</A>
    */
    public static void addToArray (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inFieldName, java.lang.String inFieldValue, java.lang.Integer inIsGUID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[4];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        args[2] = new com.intersys.cache.Dataholder(inFieldValue);
        args[3] = new com.intersys.cache.Dataholder(inIsGUID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addToArray",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method addToNamedArray in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inArrayName represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     default argument inIsGUID set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #addToNamedArray(com.intersys.objects.Database,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addToNamedArray"> Method addToNamedArray</A>
    */
    public static void addToNamedArray (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inArrayName, java.lang.String inFieldName, java.lang.String inFieldValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[4];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inArrayName);
        args[2] = new com.intersys.cache.Dataholder(inFieldName);
        args[3] = new com.intersys.cache.Dataholder(inFieldValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addToNamedArray",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method addToNamedArray in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inArrayName represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     @param inIsGUID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#addToNamedArray"> Method addToNamedArray</A>
    */
    public static void addToNamedArray (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inArrayName, java.lang.String inFieldName, java.lang.String inFieldValue, java.lang.Integer inIsGUID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[5];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inArrayName);
        args[2] = new com.intersys.cache.Dataholder(inFieldName);
        args[3] = new com.intersys.cache.Dataholder(inFieldValue);
        args[4] = new com.intersys.cache.Dataholder(inIsGUID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"addToNamedArray",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method getField in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inGUID represented as java.lang.String
     @param inFieldName represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#getField"> Method getField</A>
    */
    public static java.lang.String getField (com.intersys.objects.Database db, java.lang.String inGUID, java.lang.String inFieldName) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inGUID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"getField",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method replValue in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inValue represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.TaximeterLast#replValue"> Method replValue</A>
    */
    public static java.lang.String replValue (com.intersys.objects.Database db, java.lang.String inValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"replValue",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
}
