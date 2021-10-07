#!/bin/bash

if [[ $1 == "setup" ]]
then
	#SETUP Maven
	echo "Setting up Maven"
	if [ -d "maven" ] 
	then
		#rm manual - run 'rm --help'
		rm -r maven/
	fi
	#curl manual - run 'curl --help'
	curl -o maven.zip https://downloads.apache.org/maven/maven-3/3.8.3/binaries/apache-maven-3.8.3-bin.zip
	#unzip manual - run 'unzip --help'
	unzip maven.zip
	#mv manual - run 'mv --help'
	mv apache-maven-* maven
	#alias manual - run 'alias --help'
	#PWD is the dynamic environment variable storing the current working directory
	alias mvn=$PWD"/maven/bin/mvn"
    export PATH=../maven/bin:$PATH
    
	#SETUP Projects
	for project in *
	do
		if [ -f $project"/setup.sh" ] 
		then 
			echo "Setting up "$project
			cd $project
			bash "setup.sh"
			cd ..
		fi
	done
    
#Compile projects
elif [[ $1 == "compile" ]]
then
    export PATH=../maven/bin:$PATH
	for project in *
	do
		if [ -f $project"/pom.xml" ] 
		then 
			echo "Compiling "$project
			cd $project
			mvn clean compile
			cd ..
		fi
	done
#Test projects
elif [[ $1 == "test" ]]
then
    export PATH=../maven/bin:$PATH
	for project in *
	do
		if [ -f $project"/pom.xml" ] 
		then 
			echo "Testing "$project
			cd $project
			mvn clean test
			cd ..
		fi
	done
#Run System
elif [[ $1 == "run" ]]
then
    export PATH=../maven/bin:$PATH
    # Launch server
	echo "Launching server"
	cd "backend"
    mvn clean package -q
    java -jar target/clinic_app_server-1.0-SNAPSHOT-jar-with-dependencies.jar > serverlog.txt &
	cd ..
	# Wait 3 seconds to ensure server has launched
	
	# Launch client application
	echo "Launching client"
	cd "frontend"
	mvn javafx:run -q
	cd ..

else
	echo "Expected Usage: config.sh [command]"
	echo "Possible commands:"
	echo "  setup       will setup the system including all project "
	echo "               specific setup defined in a project's setup.sh"
	echo "  compile     will compile all projects"
	echo "  test        will test all projects"
	echo "  run         will launch the client system and locally launch"
	echo "               any necessary services"
    
fi
