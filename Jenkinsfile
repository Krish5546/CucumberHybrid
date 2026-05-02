pipeline {
	agent any
	
	stages{
		stage('Maven version') {
			bat 'mvn -v'
		}
		stages('Running Test'){
			bat 'mvn clean test'
		}
		
	}
}