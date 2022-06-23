pipeline {
    agent any

    stages {
        stage ('Run Automation Tests') {
            steps {
				echo "Start : Serenity BDD Tests"
				sh './gradlew clean bddTest -Dcucumber.filter.tags="@${Tag}"'
				echo "End : Serenity BDD Tests"
				echo "Start : Gatling Simulation"
				sh './gradlew clean gatlingRun -Dduration=${Duration} -Dusers=${TPS} -Denvironment=${Region}'
				echo "End : Gatling Simulation"
            }
        }
    }
}