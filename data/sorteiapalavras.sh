
echo "Sortei $1 palavras da lista de palavras mais frequentes do www.opensubtitles.org"
echo "Lista retirada de https://en.wiktionary.org/wiki/Wiktionary:Frequency_lists/BrazilianPortuguese_wordlist"
tail -n 4900  wordlist.freq | awk '{$2= ""; print $0}'  |  shuf -n $1 | xargs > palavras$1.txt