#!/bin/bash


wrk -t5 -c400 -d10s  http://localhost:8080/orders/new -s post.lua
