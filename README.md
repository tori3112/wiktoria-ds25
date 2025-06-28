# Distributed Systems: Clound-based deployment with IaaS

This project focuses on deploying and testing remote services (RMI, SOAP, REST) on Azure Virtual Machines across global data centres as part of the [Distributed Systems](https://onderwijsaanbod.kuleuven.be/syllabi/e/T4YDS2E.htm#activetab=doelstellingen_idm3227376) lab.

### Project Overview

Deploy and test RMI, SOAP, and REST services on Azure VMs to assess response time, throughput and performance under varying loads and from different geographic locations leveraging Azure Education program credits and free services.

For more, see the [report](https://github.com/tori3112/wiktoria-ds25/blob/main/ds-iaas-report.pdf).

### Some Instructions

Connect to VM using SSH:

```
ssh username@dns_name.region.cloundapp.azure.com
```

Move files to VM:

```
scp local/file/path username@dns_name.region.cloundapp.azure.com:[desired/remote/path]
```

Run JAR file:

```
java -jar filename
```

Check running Java processes:

```
ps aux | grep java
ps aux | grep "java -jar"
```

Process termination

```
kill pid-number
kill -9 pid-number #force if needed
```

