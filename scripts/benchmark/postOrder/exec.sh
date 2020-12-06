#!/bin/bash

cd "$(dirname "$0")"
wrk -t5 -c400 -d10s  http://localhost:8080/orders/new -s body.lua
