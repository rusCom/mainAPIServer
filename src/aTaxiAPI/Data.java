package aTaxiAPI;


/**
 * Cache' Java Class Generated for class aTaxiAPI.Data on version Cache for Windows (x86-64) 2010.2.2 (Build 600) Wed Dec 8 2010 16:37:18 EST.<br>
 *
 * @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data</A>
**/

public class Data extends aTaxiAPI.JSON implements java.io.Serializable {
    private static final long serialVersionUID = 7781;
    private static String CACHE_CLASS_NAME = "aTaxiAPI.Data";
    /**
           <p>NB: DO NOT USE IN APPLICATION(!!!).
           <br>Use <code>aTaxiAPI.Data.open</code> instead!</br></p>
           <p>
           Used to construct a Java object, corresponding to existing object
           in Cache database.
           </p>
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
    */
    public Data (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    public Data (com.intersys.objects.Database db, String initstr) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME,initstr));
    }
    /**
       Creates a new instance of object "aTaxiAPI.Data" in Cache
       database and corresponding object of class
       <code>aTaxiAPI.Data</code>.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @throws com.intersys.objects.CacheException in case of error.

              @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
              @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
     */
    public Data (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    /**
       Returns class name of the class aTaxiAPI.Data as it is in
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
        checkAllMethods(db, CACHE_CLASS_NAME, Data.class);
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#%ClassName"> Method %ClassName</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#%IsA"> Method %IsA</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#AddString"> Method AddString</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#AddString"> Method AddString</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Answer"> Method Answer</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Answer"> Method Answer</A>
    */
    public static void Answer (com.intersys.objects.Database db, java.lang.String GUID, java.lang.String inCode) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(GUID);
        args[1] = new com.intersys.cache.Dataholder(inCode);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Answer",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method ApplicationPreferences in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#ApplicationPreferences"> Method ApplicationPreferences</A>
    */
    public static java.lang.String ApplicationPreferences (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[0];
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ApplicationPreferences",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method City in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#City"> Method City</A>
    */
    public static java.lang.String City (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"City",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Client in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inClientID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Client"> Method Client</A>
    */
    public static java.lang.String Client (com.intersys.objects.Database db, java.lang.Integer inClientID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inClientID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Client",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method ClientBTS in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inClientID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#ClientBTS"> Method ClientBTS</A>
    */
    public static java.lang.String ClientBTS (com.intersys.objects.Database db, java.lang.Integer inClientID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inClientID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ClientBTS",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method FromUTF in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inText represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#FromUTF"> Method FromUTF</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#GUID"> Method GUID</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#GenerationSQL"> Method GenerationSQL</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#GenerationSQL"> Method GenerationSQL</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#GenerationSQLArray"> Method GenerationSQLArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#GenerationSQLSingleField"> Method GenerationSQLSingleField</A>
    */
    public static java.lang.String GenerationSQLSingleField (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GenerationSQLSingleField",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Order in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inOrderGUID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Order"> Method Order</A>
    */
    public static java.lang.String Order (com.intersys.objects.Database db, java.lang.Integer inOrderGUID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inOrderGUID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Order",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrderRoute in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inOrderID represented as java.lang.Integer
     default argument inHisOrder set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #OrderRoute(com.intersys.objects.Database,java.lang.Integer,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#OrderRoute"> Method OrderRoute</A>
    */
    public static java.lang.String OrderRoute (com.intersys.objects.Database db, java.lang.Integer inOrderID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inOrderID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrderRoute",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrderRoute in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inOrderID represented as java.lang.Integer
     @param inHisOrder represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#OrderRoute"> Method OrderRoute</A>
    */
    public static java.lang.String OrderRoute (com.intersys.objects.Database db, java.lang.Integer inOrderID, java.lang.Integer inHisOrder) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inOrderID);
        args[1] = new com.intersys.cache.Dataholder(inHisOrder);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrderRoute",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrderRoutePoint in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inClassID represented as java.lang.Integer
     default argument inHisOrder set to 0
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #OrderRoutePoint(com.intersys.objects.Database,java.lang.Integer,java.lang.Integer)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#OrderRoutePoint"> Method OrderRoutePoint</A>
    */
    public static java.lang.String OrderRoutePoint (com.intersys.objects.Database db, java.lang.Integer inClassID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inClassID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrderRoutePoint",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OrderRoutePoint in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inClassID represented as java.lang.Integer
     @param inHisOrder represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#OrderRoutePoint"> Method OrderRoutePoint</A>
    */
    public static java.lang.String OrderRoutePoint (com.intersys.objects.Database db, java.lang.Integer inClassID, java.lang.Integer inHisOrder) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inClassID);
        args[1] = new com.intersys.cache.Dataholder(inHisOrder);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OrderRoutePoint",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Organization in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Organization"> Method Organization</A>
    */
    public static java.lang.String Organization (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Organization",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Parking in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inParkingID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Parking"> Method Parking</A>
    */
    public static java.lang.String Parking (com.intersys.objects.Database db, java.lang.Integer inParkingID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inParkingID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Parking",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Payment in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inClassID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#Payment"> Method Payment</A>
    */
    public static java.lang.String Payment (com.intersys.objects.Database db, java.lang.Integer inClassID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inClassID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Payment",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method QiwiNumber in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#QiwiNumber"> Method QiwiNumber</A>
    */
    public static java.lang.String QiwiNumber (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"QiwiNumber",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method SQLQeurySet in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#SQLQeurySet"> Method SQLQeurySet</A>
    */
    public static void SQLQeurySet (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"SQLQeurySet",args,com.intersys.objects.Database.RET_NONE);
        return;
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addField"> Method addField</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addField"> Method addField</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addFieldSQLArray"> Method addFieldSQLArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addToArray"> Method addToArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addToArray"> Method addToArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addToNamedArray"> Method addToNamedArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#addToNamedArray"> Method addToNamedArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#getField"> Method getField</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Data#replValue"> Method replValue</A>
    */
    public static java.lang.String replValue (com.intersys.objects.Database db, java.lang.String inValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"replValue",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
}
