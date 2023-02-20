package aTaxiAPI;


/**
 * Cache' Java Class Generated for class aTaxiAPI.Taximeter on version Cache for Windows (x86-64) 2010.2.2 (Build 600) Wed Dec 8 2010 16:37:18 EST.<br>
 *
 * @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter</A>
**/

public class Taximeter extends aTaxiAPI.JSON implements java.io.Serializable {
    private static final long serialVersionUID = 5182;
    private static String CACHE_CLASS_NAME = "aTaxiAPI.Taximeter";
    /**
           <p>NB: DO NOT USE IN APPLICATION(!!!).
           <br>Use <code>aTaxiAPI.Taximeter.open</code> instead!</br></p>
           <p>
           Used to construct a Java object, corresponding to existing object
           in Cache database.
           </p>
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
    */
    public Taximeter (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    public Taximeter (com.intersys.objects.Database db, String initstr) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME,initstr));
    }
    /**
       Creates a new instance of object "aTaxiAPI.Taximeter" in Cache
       database and corresponding object of class
       <code>aTaxiAPI.Taximeter</code>.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @throws com.intersys.objects.CacheException in case of error.

              @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
              @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
     */
    public Taximeter (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    /**
       Returns class name of the class aTaxiAPI.Taximeter as it is in
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
        checkAllMethods(db, CACHE_CLASS_NAME, Taximeter.class);
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#%ClassName"> Method %ClassName</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#%IsA"> Method %IsA</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#AddString"> Method AddString</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#AddString"> Method AddString</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#Answer"> Method Answer</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#Answer"> Method Answer</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#ApplicationPreferences"> Method ApplicationPreferences</A>
    */
    public static java.lang.String ApplicationPreferences (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[0];
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ApplicationPreferences",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method Auth in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#Auth"> Method Auth</A>
    */
    public static java.lang.String Auth (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Auth",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method DriverActionsAdd in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inActionID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#DriverActionsAdd"> Method DriverActionsAdd</A>
    */
    public static void DriverActionsAdd (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inActionID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inActionID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverActionsAdd",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method DriverBusy in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#DriverBusy"> Method DriverBusy</A>
    */
    public static void DriverBusy (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverBusy",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method DriverFree in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#DriverFree"> Method DriverFree</A>
    */
    public static void DriverFree (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverFree",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method DriverOffline in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#DriverOffline"> Method DriverOffline</A>
    */
    public static void DriverOffline (com.intersys.objects.Database db, java.lang.Integer inDriverID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverOffline",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method DriverSetItem in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inFieldName represented as java.lang.String
     @param inFieldValue represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#DriverSetItem"> Method DriverSetItem</A>
    */
    public static void DriverSetItem (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.String inFieldName, java.lang.String inFieldValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inFieldName);
        args[2] = new com.intersys.cache.Dataholder(inFieldValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"DriverSetItem",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method FromUTF in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inText represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#FromUTF"> Method FromUTF</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GUID"> Method GUID</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GenerationSQL"> Method GenerationSQL</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GenerationSQL"> Method GenerationSQL</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GenerationSQLArray"> Method GenerationSQLArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GenerationSQLSingleField"> Method GenerationSQLSingleField</A>
    */
    public static java.lang.String GenerationSQLSingleField (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GenerationSQLSingleField",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetDriver in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GetDriver"> Method GetDriver</A>
    */
    public static java.lang.String GetDriver (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetDriver",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetPayment in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inPaymentID represented as java.lang.Integer
     default argument inType set to "main"
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #GetPayment(com.intersys.objects.Database,java.lang.Integer,java.lang.String)
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GetPayment"> Method GetPayment</A>
    */
    public static java.lang.String GetPayment (com.intersys.objects.Database db, java.lang.Integer inPaymentID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inPaymentID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetPayment",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetPayment in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inPaymentID represented as java.lang.Integer
     @param inType represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GetPayment"> Method GetPayment</A>
    */
    public static java.lang.String GetPayment (com.intersys.objects.Database db, java.lang.Integer inPaymentID, java.lang.String inType) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inPaymentID);
        args[1] = new com.intersys.cache.Dataholder(inType);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetPayment",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetPayments in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inLastID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GetPayments"> Method GetPayments</A>
    */
    public static java.lang.String GetPayments (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inLastID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inLastID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetPayments",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method GetPaymentsCorporateTaxi in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inLastID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#GetPaymentsCorporateTaxi"> Method GetPaymentsCorporateTaxi</A>
    */
    public static java.lang.String GetPaymentsCorporateTaxi (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inLastID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inLastID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetPaymentsCorporateTaxi",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method MessagesSend in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inText represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#MessagesSend"> Method MessagesSend</A>
    */
    public static java.lang.String MessagesSend (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.String inText) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inText);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"MessagesSend",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method PaymentOrder in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inAmount represented as java.lang.Integer
     @param inSource represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#PaymentOrder"> Method PaymentOrder</A>
    */
    public static java.lang.String PaymentOrder (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inAmount, java.lang.String inSource) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[3];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inAmount);
        args[2] = new com.intersys.cache.Dataholder(inSource);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"PaymentOrder",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method ProfelCarsDelete in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inCarID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#ProfelCarsDelete"> Method ProfelCarsDelete</A>
    */
    public static java.lang.String ProfelCarsDelete (com.intersys.objects.Database db, java.lang.Integer inCarID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inCarID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ProfelCarsDelete",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method ProfileCarsAdd in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inCarModelID represented as java.lang.Integer
     @param inCarColorID represented as java.lang.Integer
     @param inGovNumber represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#ProfileCarsAdd"> Method ProfileCarsAdd</A>
    */
    public static java.lang.String ProfileCarsAdd (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inCarModelID, java.lang.Integer inCarColorID, java.lang.String inGovNumber) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[4];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inCarModelID);
        args[2] = new com.intersys.cache.Dataholder(inCarColorID);
        args[3] = new com.intersys.cache.Dataholder(inGovNumber);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ProfileCarsAdd",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method ProfileCarsSetCurrent in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inCarID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#ProfileCarsSetCurrent"> Method ProfileCarsSetCurrent</A>
    */
    public static java.lang.String ProfileCarsSetCurrent (com.intersys.objects.Database db, java.lang.Integer inCarID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inCarID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ProfileCarsSetCurrent",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method SQLQeurySet in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inSQL represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#SQLQeurySet"> Method SQLQeurySet</A>
    */
    public static void SQLQeurySet (com.intersys.objects.Database db, java.lang.String inSQL) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inSQL);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"SQLQeurySet",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method TariffActivate in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inDriverID represented as java.lang.Integer
     @param inTariffID represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#TariffActivate"> Method TariffActivate</A>
    */
    public static java.lang.String TariffActivate (com.intersys.objects.Database db, java.lang.Integer inDriverID, java.lang.Integer inTariffID) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inDriverID);
        args[1] = new com.intersys.cache.Dataholder(inTariffID);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"TariffActivate",args,com.intersys.objects.Database.RET_PRIM);
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addField"> Method addField</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addField"> Method addField</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addFieldSQLArray"> Method addFieldSQLArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addToArray"> Method addToArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addToArray"> Method addToArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addToNamedArray"> Method addToNamedArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#addToNamedArray"> Method addToNamedArray</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#getField"> Method getField</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Taximeter#replValue"> Method replValue</A>
    */
    public static java.lang.String replValue (com.intersys.objects.Database db, java.lang.String inValue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inValue);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"replValue",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
}
