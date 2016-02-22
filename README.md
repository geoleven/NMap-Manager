The first part of the project is complete and everything should be working as 
expected. 
It was programmed in eclipse IDE environment and run with the latest Java 1.8.

If run within Eclipse, the SIGINT signal (Ctrl-C while in terminal) can be 
simulated with the:
"kill -SIGINT $(ps aux | grep NmapProject | grep -v grep | awk '{print $2}')"
or the:
"pkill -SIGINT -f NmapProject"
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
"Number of threads=#"
where # stands for the integer of the desired threads. If nothing is set, the 
file is invalid or the file does not exist at all, the program assumes a 
predifened default number of 5 threads for one time jobs.

The job files should be placed at any time inside the aforementioned path 
"~/.myNmap/" and can have any possible name except that of the property file
("properties"). There may be one or more files.

________________________________________________________________________________

The second part of the project is complete and everything should be working as 
expected (we tested it in the extent we could with the recourses we have). 
It was programmed in eclipse IDE environment and run with the latest Java 1.8.

The GUI is designed with Java Swing and a very few elements of awt. The Eclipse 
window builder was used for the basic implementation of the main frame of the 
gui but it was then programmed by hand so some more dynamic look and 
functionality could be added, making the window builder to not able to render
it fully (it should always be tested by running it properly). 

The XML results are fully parsed and converted to HTML pages which are then 
rendered within the GUI. The style-sheet used for the rendering is the default 
that comes with the nmap installation and should always exist in 
"/usr/share/nmap/nmap.xsl". We chose not to fetch the style-sheet from online 
sources because of possible incompatibilities between the various versions of 
NMap (especially considering the fact that it recently changed major version). 

The connectivity is achieved with the preferred (within the notes of this 
project) server, the Jersey server. We have used JSON object to send and receive 
everything needed. 

Minor changes were also done the SA module, so it could accomplish the new 
functionality of requesting jobs from the A.M. and parse special termination 
jobs, as well as ending specific periodic jobs, e.t.c..

The refresh rate which applies to both the request rate and the online status
refresh rate can be set to the Globals.java file in each project respectively. 

The project now has complete javadoc documentation and we have as well extracted
the online HTML form of it with a custom style-sheet for aesthetic purposes.

________________________________________________________________________________

The third part of the project is complete and everything should be working as 
expected (we tested it in the extent we could with the recourses we have). It 
was programmed in Android Studio and run with the latest Java 1.8. For emulators
we used Nexus 5 and Nexus 4 with Google API 22 and 23.

The GUI interface is designed as asked with 3 activities for the login view, 
the register view and the rest of the view. Every other view uses fragments as 
asked and because of the better performance. 

While at the main activity, there is also a drawer menu for easier navigation to
 the app. It can be used by sliding from the left part of the screen. 

The fragments are all working and sending the correct commands to the server 
which was altered to also work with the app. The server was also altered to 
enable the user registration process.

The connectivity of the web service is still using the Jersy server with JSON 
object to send and receive whatever is needed.





