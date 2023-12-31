def call(Map pipelineParams) {
    pipeline {
        agent any

        stages {
            stage('docker build') {
                steps {
                    script {
                        dockerLib.build(DockerfilePath: pipelineParams.DockerfilePath,
                                        DockerImage: pipelineParams.DockerImage,
                                        DockerContext: pipelineParams.DockerContext)
                    }
                }
            }
            stage('docker push') {
                steps {
                    script {
                        dockerLib.push(DockerImage: pipelineParams.DockerImage)
                    }
                }
            }
        }
    }
}