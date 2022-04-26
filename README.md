# PENGU_CLASSROOM

## Front end : 

#### * Run Docker 
- Download ad Run Docker Desktop: https://www.docker.com/products/docker-desktop/
- If you have the error "WSL 2 installation is incomplete":
  * Go to link: https://docs.microsoft.com/vi-vn/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package
  * Follow step 4 and 5
* Run following command in the terminal/command_prompt: 
  * ``docker pull maklisa2000/pengu_classroom:latest``
  * Check whether docker images exists: 
    * REPOSITORY: maklisa2000/pengu_classroom
    * TAG: latest
  * Run docker images: ``docker run -d -p 8081:8081 -t maklisa2000/pengu_classroom:latest``
  * Check if container maklisa2000/pengu_classroom is running: ``docker ps``