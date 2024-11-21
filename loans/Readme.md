## build docker image with buildpack
add these command into pom file
<plugin>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-maven-plugin</artifactId>
<configuration>
<!--					Build docker image with buildpacks -->
					<image>
						<name>demo/${project.artifactId}:s4</name>
					</image>
....
run to build
mvn spring-boot:build-image 