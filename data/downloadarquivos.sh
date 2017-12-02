#!/bin/bash
echo "Download de arquivos da impressao oficial"
wget -A txt -m -p -E -k -K http://aplauso.imprensaoficial.com.br/lista-livros.php?pagina={1..2}
echo "Conversão dos arquivos para UTF-8"
for file in `find aplauso.imprensaoficial.com.br/ -type f -name "*.txt" `; do
    iconv -f WINDOWS-1252 -t UTF-8 -o "$file".utf8 "$file" && mv "$file".utf8 "$file" && mv "$file" .
done
rm -rf aplauso.imprensaoficial.com.br/