#!/bin/bash

cd "$(dirname "$0")"
wrk -t12 -c800 -d30s  http://localhost:8080/products/new -s body.lua
