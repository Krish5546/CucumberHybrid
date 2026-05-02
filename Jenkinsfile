pipeline {
	agent any
	
	stages{
		stage('Maven version') {
			steps{
				bat 'mvn -v'
			}
		}
		stages('Running Test'){
			steps{
				bat 'mvn clean test'
			}
		}
		
	}
}