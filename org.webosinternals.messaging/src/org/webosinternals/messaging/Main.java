package org.webosinternals.messaging;

import com.palm.oasis.activerecord.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static Batch batch;
    private static Database PalmDB = Database.getDatabase("/var/luna/data/dbdata/PalmDatabase.db3");

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        LogEntry("Messaging Plugins");
        LogEntry("Version: 1.2.0");
        LogEntry("By Greg Roll 2009");
        LogEntry("");

        //Check commandline arguments
        if ((args.length == 0))
        {
            LogEntry("CommandName: Must be specified. Options are: RestartLuna, InstallPlugin, RemovePlugin.");
            LogEntry ("");
            System.exit(1);
        }

        //Get CommandToRun
        String CommandName = args[0];

        if (CommandName.equalsIgnoreCase(""))
        {
            LogEntry("CommandName: Must be specified. Options are: RestartLuna, InstallPlugin, RemovePlugin.");
            LogEntry ("");
            return;
        }

        //Restart Luna?
        if (CommandName.equalsIgnoreCase("RestartLuna"))
        {
            RestartLuna();
            LogEntry ("");
            return;
        }

        //Check for plugin name
        if (!(args.length == 2))
        {
            LogEntry("PluginName: Must be specified. Options are: Live, Yahoo, ICQ, Jabber, SIPE, IRC, Sametime, Groupwsie and Facebook.");
            LogEntry ("");
            System.exit(1);
        }

        //Install Plugin?
        if (CommandName.equalsIgnoreCase("InstallPlugin"))
        {
            InstallPlugin(args[1]);

            //Close DB
            PalmDB.close();

            LogEntry ("");
            return;
        }

        //Remove Plugin?
        if (CommandName.equalsIgnoreCase("RemovePlugin"))
        {
            RemovePlugin(args[1]);

            //Close DB
            PalmDB.close();

            LogEntry ("");
            return;
        }

        LogEntry("CommandName: Must be specified. Options are: RestartLuna, InstallPlugin, RemovePlugin.");
        LogEntry ("");
        return;
    }

    private static void RestartLuna() throws IOException
    {
        LogEntry("Restarting Luna...");
        Runtime.getRuntime().exec((new StringBuilder()).append("killall -HUP LunaSysMgr").toString());
    }

    private static void InstallPlugin(String PluginName) throws IOException, ClassNotFoundException, SQLException
        {
            LogEntry("Messaging Plugin Installation...");
            LogEntry("");

            if (PluginName.equalsIgnoreCase(""))
            {
                LogEntry("PluginName: Must be specified. Options are: Live, Yahoo, ICQ, Jabber, SIPE, IRC, Sametime, Groupwise, QQ and Facebook.");
                return;
            }

            //Should we install the Live Plugin?
            if (PluginName.equalsIgnoreCase("Live"))
                {
                try {
                    AddPluginToDataBase ("Live Messenger","live","{\"32x32\":\"images/accounts/messenger-32x32.png\",\"48x48\":\"images/accounts/messenger-48x48.png\"}","9901","9902");
                } catch (ActiveRecordException ex) {
                    LogEntry("Live Messenger Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the ICQ Plugin?
            if (PluginName.equalsIgnoreCase("ICQ"))
                {
                try {
                    AddPluginToDataBase ("ICQ","icq","{\"32x32\":\"images/accounts/icq-32x32.png\",\"48x48\":\"images/accounts/icq-48x48.png\"}","9903","9904");
                } catch (ActiveRecordException ex) {
                    LogEntry("ICQ Plugin Installation Error");
                    LogEntry (ex.toString());
                }
                return;
            }

            //Should we install the Facebook Plugin?
            if (PluginName.equalsIgnoreCase("Facebook"))
                {
                try {
                    AddPluginToDataBase ("Facebook Chat","facebook","{\"32x32\":\"images/accounts/facebook-32x32.png\",\"48x48\":\"images/accounts/facebook-48x48.png\"}","9905","9906");
                } catch (ActiveRecordException ex) {
                    LogEntry("Facebook Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the Jabber Plugin?
            if (PluginName.equalsIgnoreCase("Jabber"))
                {
                try {
                    AddPluginToDataBase ("Jabber","jabber","{\"32x32\":\"images/accounts/jabber-32x32.png\",\"48x48\":\"images/accounts/jabber-48x48.png\"}","9907","9908");
                } catch (ActiveRecordException ex) {
                    LogEntry("Jabber Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the SIPE Plugin?
            if (PluginName.equalsIgnoreCase("SIPE"))
                {
                try {
                    AddPluginToDataBase ("Live Communicator","sipe","{\"32x32\":\"images/accounts/sipe-32x32.png\",\"48x48\":\"images/accounts/sipe-48x48.png\"}","9909","9910");
                } catch (ActiveRecordException ex) {
                    LogEntry("SIPE Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the IRC Plugin?
            if (PluginName.equalsIgnoreCase("IRC"))
                {
                try {
                    AddPluginToDataBase ("IRC","irc","{\"32x32\":\"images/accounts/irc-32x32.png\",\"48x48\":\"images/accounts/irc-48x48.png\"}","9911","9912");
                } catch (ActiveRecordException ex) {
                    LogEntry("IRC Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the Sametime Plugin?
            if (PluginName.equalsIgnoreCase("Sametime"))
                {
                try {
                    AddPluginToDataBase ("Sametime","sametime","{\"32x32\":\"images/accounts/sametime-32x32.png\",\"48x48\":\"images/accounts/sametime-48x48.png\"}","9913","9914");
                } catch (ActiveRecordException ex) {
                    LogEntry("Sametime Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the Groupwise Plugin?
            if (PluginName.equalsIgnoreCase("Groupwise"))
                {
                try {
                    AddPluginToDataBase ("Novell Groupwise","gwim","{\"32x32\":\"images/accounts/novell-32x32.png\",\"48x48\":\"images/accounts/novell-48x48.png\"}","9915","9916");
                } catch (ActiveRecordException ex) {
                    LogEntry("Groupwise Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the QQ Plugin?
            if (PluginName.equalsIgnoreCase("QQ"))
                {
                try {
                    AddPluginToDataBase ("QQ","qqim","{\"32x32\":\"images/accounts/qq-32x32.png\",\"48x48\":\"images/accounts/qq-48x48.png\"}","9917","9918");
                } catch (ActiveRecordException ex) {
                    LogEntry("Groupwise Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we install the Yahoo Plugin?
            if (PluginName.equalsIgnoreCase("Palm"))
                {
                try {
                    EnablePalmPlugins();
                } catch (ActiveRecordException ex) {
                    LogEntry("Palm Plugin Installation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            LogEntry("PluginName: Must be specified. Options are: Live, Yahoo, ICQ, Jabber, SIPE, IRC, Sametime, Groupwise, QQ and Facebook.");
            return;
        }

    private static void RemovePlugin(String PluginName) throws IOException, ClassNotFoundException, SQLException
        {
            LogEntry("Messaging Plugin Uninstallation...");
            LogEntry("");

            if (PluginName.equals(""))
            {
                LogEntry("PluginName: Must be specified. Options are: Live, Yahoo, ICQ, Jabber, SIPE, IRC, Sametime, Groupwise, QQ and Facebook.");
                return;
            }

            //Should we uninstall the Live Plugin?
            if (PluginName.equalsIgnoreCase("Live"))
                {
                try {
                    RemovePluginFromDataBase ("Live Messenger");
                } catch (ActiveRecordException ex) {
                    LogEntry("Live Messenger Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the ICQ Plugin?
            if (PluginName.equalsIgnoreCase("ICQ"))
                {
                try {
                    RemovePluginFromDataBase ("ICQ");
                } catch (ActiveRecordException ex) {
                    LogEntry("ICQ Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the Facebook Plugin?
            if (PluginName.equalsIgnoreCase("Facebook"))
                {
                try {
                    RemovePluginFromDataBase ("Facebook Chat");
                } catch (ActiveRecordException ex) {
                    LogEntry("Facebook Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the Yahoo Plugin?
            if (PluginName.equalsIgnoreCase("Palm"))
                {
                try {
                    DisablePalmPlugins();
                } catch (ActiveRecordException ex) {
                    LogEntry("Palm Plugin Uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the Jabber Plugin?
            if (PluginName.equalsIgnoreCase("Jabber"))
                {
                try {
                    RemovePluginFromDataBase ("Jabber");
                } catch (ActiveRecordException ex) {
                    LogEntry("Jabber Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the SIPE Plugin?
            if (PluginName.equalsIgnoreCase("SIPE"))
                {
                try {
                    RemovePluginFromDataBase ("Live Communicator");
                } catch (ActiveRecordException ex) {
                    LogEntry("SIPE Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the IRC Plugin?
            if (PluginName.equalsIgnoreCase("IRC"))
                {
                try {
                    RemovePluginFromDataBase ("IRC");
                } catch (ActiveRecordException ex) {
                    LogEntry("IRC Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the Sametime Plugin?
            if (PluginName.equalsIgnoreCase("Sametime"))
                {
                try {
                    RemovePluginFromDataBase ("Sametime");
                } catch (ActiveRecordException ex) {
                    LogEntry("Sametime Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            //Should we uninstall the Groupwise Plugin?
            if (PluginName.equalsIgnoreCase("Groupwise"))
                {
                try {
                    RemovePluginFromDataBase ("Novell Groupwise");
                } catch (ActiveRecordException ex) {
                    LogEntry("Groupwise Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

             //Should we uninstall the QQ Plugin?
            if (PluginName.equalsIgnoreCase("QQ"))
                {
                try {
                    RemovePluginFromDataBase ("QQ");
                } catch (ActiveRecordException ex) {
                    LogEntry("Groupwise Plugin uninstallation Error");
                    LogEntry(ex.toString());
                }
                return;
            }

            LogEntry("PluginName: Must be specified. Options are: Live, Yahoo, ICQ, Jabber, SIPE, IRC, Sametime, Groupwise, QQ and Facebook.");
            System.exit(1);
        }

    private static void AddPluginToDataBase(String AccountDisplayName, String ClassID, String IconPaths, String ModNumber1, String ModNumber2) throws ActiveRecordException, IOException, ClassNotFoundException, SQLException
    {
            LogEntry("Installing " + AccountDisplayName + " Plugin...");

            int numberOfTimes = 3; //Number of times to try and update the DB

            //Get Installation ID
            String InstallationID = GetID("com_palm_accounts_AccountType", AccountDisplayName);

            if (!(InstallationID.equalsIgnoreCase("")))
            {
                LogEntry(AccountDisplayName + " Plugin already installed.");
                return;
            }

            //Update com_palm_account_AccountType
            String tableName = ActiveRecord.tableName(com.palm.accounts.AccountType.class);

            //Get Last ID
            long LastIDTable1 = GetNext ("com_palm_accounts_AccountType", "id");

            //Add new entry
            try
            {
            for(int x = 0; x < numberOfTimes; x++)
                {
                        batch = Batch.create();
                        batch.setDatabase(PalmDB);
                        batch.sqlWrite((new StringBuilder()).append("INSERT INTO ").append(tableName).append(" (name,accountClass,domain,iconClass,lunaSetupServiceMethod,accountDeleteMethod,icons,isCrudAccount,isUserCreatable,dataIsReadOnly,acctTypeEnabled,hasSharedAuthToken,backupID,appID,id,_class_id,_mod_num,_flags) VALUES ('" + AccountDisplayName + "','com.palm.messaging.accounts.IMAccount','" + ClassID + "','" + ClassID + "','luna://com.palm.messaging/createAccountAndLogin','','" + IconPaths + "','0','1','0','1','0','0','defaultAppId','" + LastIDTable1 + "','1','" + ModNumber1 + "','')").toString());
                        batch.execute();
                }
            }
            catch (Exception ex)
            {
                LogEntry("Installation of " + AccountDisplayName + " Plugin (Part 1) appears successful. No need to try again");
            }

            //Update com_palm_account_AccountTypeService
            tableName = ActiveRecord.tableName(com.palm.accounts.AccountTypeService.class);

            //Get Last ID
            long LastIDTable2 = GetNext ("com_palm_accounts_AccountTypeService", "id");

            //Add new entry
            try
            {
                for(int x = 0; x < numberOfTimes; x++)
                {
                        batch = Batch.create();
                        batch.setDatabase(PalmDB);
                        batch.sqlWrite((new StringBuilder()).append("INSERT INTO ").append(tableName).append(" (com_palm_accounts_AccountType_id,interfaceName,dbusAddress,serviceType,isCrudService,backupID,id,_class_id,_mod_num,_flags) VALUES ('" + LastIDTable1 + "','','im.libpurpleext.greg','IM','0','0','" + LastIDTable2 + "','2','" + ModNumber2 + "','')").toString());
                        batch.execute();
                }
            }
            catch (Exception ex)
            {
                LogEntry("Installation of " + AccountDisplayName + " Plugin (Part 2) appears successful. No need to try again");
            }

            //Done
            LogEntry(AccountDisplayName + " Plugin Installed.");
    }

    private static void RemovePluginFromDataBase(String AccountDisplayName) throws ActiveRecordException, IOException, ClassNotFoundException, SQLException
    {
            LogEntry("Uninstalling " + AccountDisplayName + " Plugin...");

            //Update com_palm_account_AccountType
            String tableName = ActiveRecord.tableName(com.palm.accounts.AccountType.class);

            //Get Removal ID
            String RemovalID = GetID("com_palm_accounts_AccountType", AccountDisplayName);

            //Add new entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("DELETE FROM ").append(tableName).append(" WHERE name='" + AccountDisplayName + "'").toString());
            batch.execute();

            //Update com_palm_account_AccountTypeService
            tableName = ActiveRecord.tableName(com.palm.accounts.AccountTypeService.class);

            //Add new entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("DELETE FROM ").append(tableName).append(" WHERE com_palm_accounts_AccountType_id='" + RemovalID + "'").toString());
            batch.execute();

            //Done
            LogEntry(AccountDisplayName + " Plugin Uninstalled.");
    }

     private static void DisablePalmPlugins() throws ActiveRecordException, IOException, ClassNotFoundException, SQLException
    {
            LogEntry("Disabling Yahoo Plugin...");

            //Update com_palm_account_AccountType
            String tableName = ActiveRecord.tableName(com.palm.accounts.AccountType.class);

            //Update Palm entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("UPDATE ").append(tableName).append(" SET acctTypeEnabled=0 WHERE name='Yahoo IM'").toString());
            batch.execute();

            //Update com_palm_account_AccountTypeService
            tableName = ActiveRecord.tableName(com.palm.accounts.AccountTypeService.class);

            //Update palm entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("UPDATE ").append(tableName).append(" SET dbusAddress='com.nokia.imtransport' WHERE com_palm_accounts_AccountType_id='1099511627792'").toString());
            batch.execute();

            //Done
            LogEntry("Yahoo Plugin Disabled.");

            LogEntry("Configuring Palm Plugins.");
            
            //Update palm entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("UPDATE ").append(tableName).append(" SET dbusAddress='im.libpurple.palm' WHERE dbusAddress='im.libpurpleext.greg'").toString());
            batch.execute();

            //Done
            LogEntry("Palm Plugins Configured.");
    }

    private static void EnablePalmPlugins() throws ActiveRecordException, IOException, ClassNotFoundException, SQLException
    {
            LogEntry("Enabling Yahoo Plugin...");

            //Update com_palm_account_AccountType
            String tableName = ActiveRecord.tableName(com.palm.accounts.AccountType.class);

            //Update Palm entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("UPDATE ").append(tableName).append(" SET acctTypeEnabled=1 WHERE name='Yahoo IM'").toString());
            batch.execute();

            //Update com_palm_account_AccountTypeService
            tableName = ActiveRecord.tableName(com.palm.accounts.AccountTypeService.class);

            //Update palm entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("UPDATE ").append(tableName).append(" SET dbusAddress='im.libpurpleext.greg' WHERE dbusAddress='com.nokia.imtransport'").toString());
            batch.execute();

            //Done
            LogEntry("Yahoo Plugin Enabled.");

            LogEntry("Configuring Palm Plugins.");

            //Update palm entry
            batch = Batch.create();
            batch.setDatabase(PalmDB);
            batch.sqlWrite((new StringBuilder()).append("UPDATE ").append(tableName).append(" SET dbusAddress='im.libpurpleext.greg' WHERE dbusAddress='im.libpurple.palm'").toString());
            batch.execute();

            //Done
            LogEntry("Palm Plugins Configured.");
    }

    private static void LogEntry(String Value) throws IOException
     {
            System.out.println(Value);

            String tmpFilePath = System.getProperty("java.io.tmpdir");
            BufferedWriter LogFile = new BufferedWriter(new FileWriter(tmpFilePath + "/MessagingPlugins.log", true));

            try {
              //Write to log file
              LogFile.newLine();
              LogFile.write (Value);
            }
            finally {
              LogFile.close();
            }
     }

    private static long GetNext(String TableName, String ColumnName) throws ClassNotFoundException, SQLException, IOException
    {
        LogEntry ("Querying " + TableName + " for last " + ColumnName + "...");
        long NextValue = 0;
        String ColumnValue = null;

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/var/luna/data/dbdata/PalmDatabase.db3");
        java.sql.Statement stat = conn.createStatement();

        ResultSet rs = stat.executeQuery("select * from " + TableName + ";");
        while (rs.next()) {
            ColumnValue = rs.getString(ColumnName);
        }
        rs.close();
        conn.close();

        LogEntry ("Found: " + ColumnValue);

        NextValue = Long.parseLong(ColumnValue.trim());
        NextValue += 1;

        LogEntry ("Next " + ColumnName + " is: " + NextValue);
        return NextValue;
    }

    private static String GetID(String TableName, String PluginName) throws ClassNotFoundException, SQLException, IOException
    {
        LogEntry ("Querying " + TableName + " for id...");
        String ColumnValue = "";

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/var/luna/data/dbdata/PalmDatabase.db3");
        java.sql.Statement stat = conn.createStatement();

        ResultSet rs = stat.executeQuery("select * from " + TableName + " where name='" + PluginName + "';");
        while (rs.next()) {
            ColumnValue = rs.getString("id");
        }
        rs.close();
        conn.close();

        LogEntry ("Found: " + ColumnValue);

        return ColumnValue;
    }
}