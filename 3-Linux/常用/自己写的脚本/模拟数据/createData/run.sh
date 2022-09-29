#./sh/run.sh
chmod +x ./sh/run.sh

echo "">result.out
./sh/run.sh >result.out 2>&1 &

echo $!
echo $!>run.pid