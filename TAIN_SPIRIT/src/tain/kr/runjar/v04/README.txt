
Manifest-Version: 1.0
 
Package: com.darwinsys.util
 
Created-By: 1.3.0_02 (Sun Microsystems Inc.)
 
Title: Darwin Open Systems' Utility Classes
  libs/ant.jar
  libs/gson-2.6.2.jar
  libs/commons-net-3.3.jar
 
Provider: Ian Darwin, ian@darwinsys.com
 
Class-Path: .
  libs/log4j-1.2.17.jar
 
Main-Class: tain.kr.runjar.v03.JarRsrcLoader
 
Rsrc-Class-Path: ./
  libs/log4j-1.2.17.jar
 
Rsrc-Main-Class: tain.kr.runjar.v03.TestMainRunJar
 

------------------------------------------------------------

Manifest-Version: 1.0
Package: tain.kr.test
Created-By: 1.0 (TAIN Inc.)
Title: CoSmarter of TAIN
Provider: Kiea Seok Kang    kiea@tain.co.kr  at 16.04.15
Class-Path: .
Main-Class: tain.kr.runjar.v03.JarRsrcLoader
Rsrc-Class-Path: ./
     libs/commons-net-3.3.jar
     libs/log4j-1.2.17.jar
     libs/ant.jar
Rsrc-Main-Class: tain.kr.test.main.Main



-------------------------------------------------

jar cvfM ../tain-runjar-1.0.jar *

java -jar ../tain-runjar-1.0.jar
