# ELFDynamicSystem
The Augmented Reality sandbox software of the Europe's Lost Frontiers project.

Requirements
------------

Windows

Any Microsoft Kinect

A box of sand

A short throw LCD projector

A means of suspending the projector and Kinect over the sandbox

Eclipse

Instructions for use
--------------------

These instructions cover how I run the software, which is through Eclipse. This is less elegant than creating an executable file but I often end up changing variables between simulation runs so I find it easiest.

1. Create an AR sandbox by using the same method as directed for [Oliver Kreylos' sandbox](https://web.cs.ucdavis.edu/~okreylos/ResDev/SARndbox/index.html).
2. Download and install the Eclipse IDE for Java development from [here](https://www.eclipse.org/).
3. Download and install a Java runtime Environment from [here](https://www.java.com/en/download/manual.jsp).
4. Download and install the [Kinect Windows SDK](https://docs.microsoft.com/en-us/windows/apps/design/devices/kinect-for-windows).
5. Download the [ELFDynamicSystem repository](https://github.com/ELFdev001/ELFDynamicSystem).
6. Create a new Java project in Eclipse and point it towards the ELFDynamicSystem directory.
7. Change the directory in BDmain to be the directory in which the default landscape image (BDlandscape002.jpg) is found.
8. Make sure the TESTING boolean in BDmain is set to true if you want to not connect to a Kinect (for instance if you don't have one), or false if you want to
try and use a Kinect
9. Run BDmain

Obviously skip any step that involves installing software you already have. I've found no problems with compatibility with the JRE so far. The main potential for problems seems to be in the installation of the Kinect drivers as part of the Kinect Windows SDK. Follow the instructions here carefully because I've had problems when plugging the Kinect in at the wrong time.

Keys
----
'q' - Increase temperature, reducing ice caps and raising water level.

'a' - Decrease temperature, increasing ice caps and reducing water level.

'h' - Toggles height mode, which gets rid of the plants and replaces the background with a greyscale heightmap and some rough contours.

Alt-F4 - Closes the simulation and kicks you back to Eclipse 

Hints
-----
Everything works better if the resolution of the PC is set to 1280x960. The sandbox has a 4:3 aspect ratio to match the Kinect and the projector so it's easier if the PC does too. Other resolutions can be adjusted by alterind the CELLWIDTH and CELLHEIGHT values in BDframe. By default they are both set to 4 as the default Kinect resolution is 320x240. This uses a 4x4 block pixels for every cell of the 320x240 environment, giving a total size of 1280x960. If you need to set the screen resoltuion to 1280 x 720 you would change CELLHEIGHT to 3.

This software does not use the complex calibration process of Oliver Kreylos' software and relies on adjusting the projector and Kinect to get everything close enough. I've found that it's relatively easy to do this to the extent that it doesn't ruin the experience for the user.

The software only runs for about 15 minutes before self-quitting. I forget why this was ever in the software but I found that it is useful in public settings as some users, especially children, can monopolise the sandbox almost indefinitely. With a natural break every 15 minutes they tend to move onto something else at that point.

Credits
-------

Software development was done by Phil Murgatroyd, over the example framework created by Angelos Barmpoutis.

This software relies heavily on Angelos Barmpoutis and the University of Florida Digital Worlds Institute's J4K Library (https://research.dwi.ufl.edu/projects/ufdw/j4k/)
so a huge thanks to them for putting the work in.

Thanks to Professor Kai Bongs at the University of Birmingham for generously providing a projector.

This software developed out of the GG-TOP project at Birmingham, and was completed thanks to the ERC-funded 'Europe's Lost Frontiers' project and the
School of Archaeological and Forensic Science at the University of Bradford.
