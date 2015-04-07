#!/bin/bash

IMAGE_HOME=/home/guilherme/FGA/TCC/git/figuras/images

cd $IMAGE_HOME
rm -f ../*.eps

convert *.png -set filename:fname '%t' +adjoin '%[filename:fname].eps'

for i in $(ls *.eps | grep -v capa)
do 
	mv $i ../
done

cp capa.eps ../
