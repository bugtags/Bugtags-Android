#! /bin/bash
echo "Push Docs..."
echo "input your ci comment log:"

read comment

if [ "$comment" == "" ]
    then exit 1
fi

git add *
git commit -m "$comment"
git push origin master
git push github master
