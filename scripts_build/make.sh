#!/bin/sh -e

# Separate version-subversion by '_'
# E.g.: '4_1' (instead of '4.1')
export VERSION=4.3
export VERSION_UND=`echo $VERSION | tr '.' '_'`

#---
# Build SnpEff
#---

cd $SNPEFF

mvn clean compile assembly:assembly

# Install JAR file in local Maven repo
mvn install:install-file \
	-Dfile=target/SnpEff-$VERSION.jar \
	-DgroupId=org.snpeff \
	-DartifactId=SnpEff \
	-Dversion=$VERSION \
	-Dpackaging=jar \
	-DgeneratePom=true \
	--quiet

cp target/SnpEff-$VERSION-jar-with-dependencies.jar $SNPEFF/snpEff.jar

	
# cd - 

#---
# Build SnpSift
#---
# cd $HOME/workspace/SnpSift/

# mvn clean compile assembly:assembly

# cp target/SnpSift-$VERSION-jar-with-dependencies.jar $HOME/snpEff/SnpSift.jar

# # Install JAR file in local Maven repo
# mvn install:install-file \
	# -Dfile=target/SnpSift-$VERSION.jar \
	# -DgroupId=org.snpsift \
	# -DartifactId=SnpSift \
	# -Dversion=$VERSION \
	# -Dpackaging=jar \
	# -DgeneratePom=true \
	# --quiet

# cd - 

# #---
# # Update galaxy databases
# #---
# ./scripts_build/galaxy.sh

echo "Build done!"
