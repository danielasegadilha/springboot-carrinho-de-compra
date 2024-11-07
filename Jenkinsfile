pipeline {
    agent any

    stages {
        stage('Verificar Repositório') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/victor']], useRemoteConfigs:[[url: 'https://github.com/danielasegadilha/springboot-carrinho-de-compra.git']]])
            }
        }

        stage('Instalar Dependências e Build') {
            steps {
                script {
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Construir Imagem Docker') {
            steps {
                script {
                    def appName = 'grupo1.carrinhodecompra'
                    def imageTag = "${appName}:${env.BUILD_ID}"  // Usando o BUILD_ID do Jenkins como tag da imagem Docker
                    bat "docker build -t ${imageTag} -f Dockerfile ."
                }
            }
        }

        stage('Fazer Deploy') {
            steps {
                script {
                    def appName = 'grupo1.carrinhodecompra'
                    def imageTag = "${appName}:${env.BUILD_ID}"

                    bat "docker stop ${appName} || exit 0"  
                    bat "docker rm ${appName}  || exit 0"  
                }
            }
        }
    }
}
                  
