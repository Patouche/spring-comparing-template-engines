#!/bin/bash

echo "Apache benchmark ...."
ab -V
if [ ! $? ] ; then echo "Missing apache benmark..."; exit 1; fi

SOLUTIONS=("jsp" "freemarker" "velocity" "thymeleaf" "jade" "scalate" "mustache" "pebble" "handlebars" "jtwig");

for s in ${SOLUTIONS[*]} ; do
	echo "Apache benchmark of solutions $s"
	log=$(ab -n 25000 -c 25 -k http://localhost:8080/jsp 2>&1)
	echo "$log" | grep "Failed requests:"
	echo "$log" | grep "Write errors:"
	echo "$log" | grep "Time per request.*(mean)"
done





