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

1. Create an AR sandbox by using the same method as directed for [Oliver Kreylos' sandbox](https://web.cs.ucdavis.edu/~okreylos/ResDev/SARndbox/index.html).
2. Download and install the Eclipse IDE for Java development from [here](https://www.eclipse.org/).
3. Download the [ELFDynamicSystem repository](https://github.com/ELFdev001/ELFDynamicSystem).
4. Create a new Java project in Eclipse and point it towards the ELFDynamicSystem directory.
5. Change the directory in BDmain to be the directory in which the default landscape image (BDlandscape002.jpg) is found.
6. Make sure the TESTING boolean in BDmain is set to true if you want to not connect to a Kinect (for instance if you don't have one), or false if you want to
try and use a Kinect
7. Run BDmain

Keys
----
'q' - Increase temperature, reducing ice caps and raising water level.

'a' - Decrease temperature, increasing ice caps and reducing water level.

'h' - Toggles height mode, which gets rid of the plants and replaces the background with a greyscale heightmap and some rough contours.

't' - Toggles tree mode, which seems a bit broken at the moment.

Credits
-------

Most software development was done by Phil Murgatroyd, although the trees were based on Micheál Butler's work.

This software relies heavily on Angelos Barmpoutis and the University of Florida Digital Worlds Institute's J4K Library (https://research.dwi.ufl.edu/projects/ufdw/j4k/)
so a huge thanks to them for putting the work in.

Thanks to Professor Kai Bongs at the University of Birmingham for generously providing a projector.

This software developed out of the GG-TOP project at Birmingham, and was completed thanks to the ERC-funded 'Europe's Lost Frontiers' project and the
School of Archaeological and Forensic Science at the University of Bradford.
