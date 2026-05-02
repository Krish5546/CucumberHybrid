pipeline {
	agent any
	
	stages{
		stage('Maven version') {
			steps{
				bat 'mvn -v'
			}
		}
		stages('Running Test'){
			Steps{
				bat 'mvn clean test'
			}
		}
		
	}
}