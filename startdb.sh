#!/bin/bash
mvn exec:java -Dexec.mainClass="org.hsqldb.Server" -Dexec.args="-database.0 file:target/data/hdb -dbname.0 xdb"
