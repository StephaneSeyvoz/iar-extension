/**
 * 
 * Copyright Assystem 2011
 * Author  : Stéphane Seyvoz
 * Contact : sseyvoz@assystem.com 
 * 
 */
 
 This software is a plugin enabling IAR compiler and linker support for the
 Mindc toolchain (http://mind.ow2.org). It enables the use of IAR-specific
 preprocessing, compilation, and linking flags, as the original Mindc only
 supported GCC and LD.
 
 French detailed information from a discussion between Matthieu Leclercq
 (ST Microelectronics developer maintaining Mind) and me :
 
Par défaut, MIND suppose que le compilateur C est compatible avec GCC, mais
il est possible de faire une extension à mindc qui change ce comportement.
Dans l’architecture de la toolchain il y a un composant qui s’appelle le
« CompilerWrapper ». Par défaut il n’y a qu’une seule implémentation de ce
composant qui est « GccCompilerWrapper » mais il est possible d’en implémenter
d’autres pour des suites d’outil de compil qui ne sont pas compatible avec GCC.

Il faut donc développer une extension de mindc. Ci-joint un squelette pour
cette extension. Il contient :

- 	Une classe IARCompilerWrapper qui étend le GCCCompilerWrapper et qui doit
	être complétée pour adapter la ligne de commande générée pour exécuter le
	préprocesseur, le compilateur et le linker.
- 	Une classe IARCompilerModule. La toolchain MIND utilise un outil
	d’injection de dépendance nommé ‘Google Guice’. Cette classe
	IARCompilerModule est un module Guice qui spécifie que l’interface
	« CompilerWrapper » est implémentée par la class « IARCompilerWrapper ».
	Ainsi les composants de la toolchain MIND qui utilise le CompilerWrapper,
	utiliseront une instance de cette nouvelle classe.
- 	Un fichier ‘mind-plugin.xml’. C’est le descripteur de l’extension. Le
	système de plugin de MIND reprend les concepts d’extensions et de points
	d’extension de Eclipse. Dans ce fichiers ont décrit donc les extensions
	qui se branchent sur la toolchain. Ce fichiers contient 2 extensions :
	
	o   La première ajoute le flag ‘—iar’ sur la ligne de commande ‘mindc’.
		Avec cette extension, ‘mindc’ peut reconnaitre le flag ‘’iar’ sur la
		ligne de commande.
	o   La deuxième ajoute le module Guice « IARCompilerModule ». Ce module
		surcharge le module « CommonBackendModule » afin de remplacer
		l’implémentation par défaut de l’interface « CompilerWrapper » qui est
		spécifiée dans ce module. De plus, cette deuxième extension n’est
		active que si le flag ‘—iar’ est présent sur la ligne de commande de
		mindc. Ceci permet d’utiliser le IARCompilerWrapper si et seulement si
		le flag ‘—iar’ est spécifié sur la ligne de commande de mindc.

Pour utiliser cette extension il suffit de la compiler avec maven ‘mvn package’
et de placer le fichier JAR produit dans le répertoire ‘ext’ de mind. Ensuite
il faut lancer mindc avec l’option ‘—iar’.