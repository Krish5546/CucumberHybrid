pipeline {
	agent any
	
	stages{
		stage('Maven version') {
			steps{
				bat 'mvn -v'
			}
		}
		stage('Running Test'){
			steps{
				bat 'mvn clean test'
			}
		}
		
	}
}