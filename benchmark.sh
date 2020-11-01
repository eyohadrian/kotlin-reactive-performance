#!/bin/bash

# wrk -t1 -c4 -d30s http://localhost:8080/generate?test=1

wrk -t1 -c4 -d3s http://localhost:8080/generate
