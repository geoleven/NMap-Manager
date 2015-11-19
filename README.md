The first part of the project is complete and everything should be working as 
expected. 
It was programmed in eclipse IDE environment and run with the latest Java 1.8.

If run within Eclipse, the SIGINT signal (Ctrl-C while in terminal) can be 
simulated with the:
kill -SIGINT $(ps aux | grep NmapProject | grep -v grep | awk '{print $2}')
or the:
pkill -SIGINT -f NmapProject
commands assuming the name of the project (and/or the binary) has not be changed
to something different. 

The program creates and uses the ~/.myNmap directory as its main directory for 
storing its property and job files. It should have the proper rights to access 
this folder, or it will exit as it assumes it cannot run any jobs at all.

Because of the need for sudo for the nmap to run with some flags 
(for example -O) the sudo file of the system runing the program should be edited
with "sudo visudo" and the line "username ALL=(ALL) NOPASSWD: /usr/bin/nmap" 
must be added to its last line.

The properties of the programm (one time job threads' number as for now) should 
be written in a file named "properties" and placed in the aforementioned path 
"~/.myNmap/". The notation for setting the threads is:
Number of threads=#
where # stands for the integer of the desired threads. If nothing is set, the 
file is invalid or the file does not exist at all, the program assumes a 
predifened default number of 5 threads for one time jobs.
