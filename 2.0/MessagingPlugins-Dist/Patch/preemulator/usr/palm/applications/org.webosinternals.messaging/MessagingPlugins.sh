#!/bin/sh

#Run Java
echo "Running Java..."
javahy -bcp /usr/lib/luna/java/Utils.jar:/usr/lib/luna/java/accounts.jar:/usr/lib/luna/java/accountservices.jar:/usr/lib/luna/java/activerecord.jar:/usr/lib/luna/java/json.jar:/usr/lib/luna/java/sqlitejdbc-v053.jar:$1/usr/palm/applications/org.webosinternals.messaging/org.webosinternals.messaging.jar org.webosinternals.messaging.Main $2 $3