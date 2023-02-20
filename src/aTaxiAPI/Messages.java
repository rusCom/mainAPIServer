package aTaxiAPI;


/**
 * Cache' Java Class Generated for class aTaxiAPI.Messages on version Cache for Windows (x86-64) 2010.2.2 (Build 600) Wed Dec 8 2010 16:37:18 EST.<br>
 *
 * @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Messages</A>
**/

public class Messages extends com.intersys.classes.RegisteredObject implements java.io.Serializable {
    private static final long serialVersionUID = 4155;
    private static String CACHE_CLASS_NAME = "aTaxiAPI.Messages";
    /**
           <p>NB: DO NOT USE IN APPLICATION(!!!).
           <br>Use <code>aTaxiAPI.Messages.open</code> instead!</br></p>
           <p>
           Used to construct a Java object, corresponding to existing object
           in Cache database.
           </p>
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
    */
    public Messages (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    public Messages (com.intersys.objects.Database db, String initstr) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME,initstr));
    }
    /**
       Creates a new instance of object "aTaxiAPI.Messages" in Cache
       database and corresponding object of class
       <code>aTaxiAPI.Messages</code>.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @throws com.intersys.objects.CacheException in case of error.

              @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
              @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
     */
    public Messages (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    /**
       Returns class name of the class aTaxiAPI.Messages as it is in
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
        checkAllMethods(db, CACHE_CLASS_NAME, Messages.class);
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Messages#%ClassName"> Method %ClassName</A>
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
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Messages#%IsA"> Method %IsA</A>
    */
    public static java.lang.Integer sys_IsA (com.intersys.objects.Database db, java.lang.String isclass) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(isclass);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%IsA",args,com.intersys.objects.Database.RET_PRIM);
        return res.getInteger();
    }
    /**
     <p>Runs method Add in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inData represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Messages#Add"> Method Add</A>
    */
    public static java.lang.String Add (com.intersys.objects.Database db, java.lang.String inData) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(inData);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"Add",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method AddCallCode in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inPhone represented as java.lang.String
     @param inCode represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Messages#AddCallCode"> Method AddCallCode</A>
    */
    public static java.lang.String AddCallCode (com.intersys.objects.Database db, java.lang.String inPhone, java.lang.String inCode) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inPhone);
        args[1] = new com.intersys.cache.Dataholder(inCode);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"AddCallCode",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method SetStatus in Cache.</p>
     @param db represented as com.intersys.objects.Database
     @param inMessageID represented as java.lang.Integer
     @param inStatus represented as java.lang.String
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://WIN-S2LFE1VCB4Q:57772/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=ATAXI&CLASSNAME=aTaxiAPI.Messages#SetStatus"> Method SetStatus</A>
    */
    public static void SetStatus (com.intersys.objects.Database db, java.lang.Integer inMessageID, java.lang.String inStatus) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[2];
        args[0] = new com.intersys.cache.Dataholder(inMessageID);
        args[1] = new com.intersys.cache.Dataholder(inStatus);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"SetStatus",args,com.intersys.objects.Database.RET_NONE);
        return;
    }
}
