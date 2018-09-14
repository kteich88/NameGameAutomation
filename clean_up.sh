#!/bin/bash
yel=$'\e[1;33m'
cyn=$'\e[1;36m'
mag=$'\e[1;35m'
red=$'\e[1;31m'
end=$'\e[0m'

printf "%s\n" "${yel}Begin screenshot cleanup...${end}"

rm -r ./webdriverScreenshot/*

printf "%s\n" "${cyn}Your screenshot files have been removed. Happy testing!${end}"

