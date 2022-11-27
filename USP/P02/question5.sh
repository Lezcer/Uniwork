for x in *.htm;
do
    mv "$x" "${x%.htm}.html";
done
