clean:
	./gradlew clean

build:
	./gradlew clean build

start:
	./gradlew run


install:
	./gradlew installDist

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

check-updates:
	./gradlew dependencyUpdates
