pipeline 
{
    agent any

    stages 
    {
        stage('UI Test') 
        {
            steps 
            {
                build 'FirstJenkinsJobApril'
            }
        }

          stage('API Test') 
        {
            steps 
            {
                build 'SecondJenkinsJobApril'
            }
        }


          stage('Pefor Test') 
        {
            steps 
            {
                build 'RunSeleniumTestFromGit'
            }
        }
    }
}
