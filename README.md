# JavaGame: Truy tìm kho báu
- Hôm nay 29/4/2022, ngày nộp báo cáo BTL môn java
- Đây là tựa game đầu tiên bằng java do mình code. 
- Để có thể hoàn thiện game này mình đã học cách làm game 2D từ rất nhiều nguồn khác nhau, sau đó mình đã tự nghĩ ra con game này, và xây dựng nó theo những quy tắc mình  học được: cách đặt tên, biến, package, class, ... sao cho phù hợp
-  Và bạn biết đấy, thời gian chủ yếu là fix bug vì không phải thứ gì mình học được nó cũng chạy đúng theo ý mình, LOL :V
-  Có thể còn nhiều thứ chưa được hoàn thiện và cũng đc góp ý từ thầy dạy bộ môn. 
$ Mặc dù đã kết thúc môn học nhưng mình vẫn sẽ tiếp tục phát triển trò chơi này.
# Mình sẽ : 
1. Thiết lập cơ sở dữ liệu (mình cũng chưa biết sẽ lưu gì :v, hmm có lẽ là điểm số của các màn khi random, tên người chiến thắng chẳng hạn)
2. Random map (ý tưởng mình sẽ lưu mảng 2 chiều)
3. Việc random map sẽ ảnh hưởng tới việc đặt ví trí các OBJ_ nên mình sẽ nghĩ cách sử lí phần này khi ramdom map 
- Ý tưởng 1: Sẽ đặt các số 2345... khi loadmap, nhưng khi mình ăn (va chạm) vật thể nó sẽ biến mất nên khi đọc map mình sẽ kiểm tra các số >2 lưu ra biến OBJ để sử lí,
 xong rồi mình gắn lại vị trí đó là O-cỏ để tiếp tục vẽ map (draw)
- Ý tưởng 2: Ở kèm với mỗi map__.txt mình sẽ thêm 1 số dòng ở đầu, mỗi dòng sẽ lưu vị trí OBJ_, khi đọc file map mình sẽ lấy ra và xử lí


- Và ... còn gì nữa mình sẽ update sau. OK 
- À cách mình random map là dựa vào Web này: https://www.dcode.fr/maze-generator
- Ban đầu mình có thử 1 số cách random map code bằng C/C++ nhưng mình thấy thuật toán nó không tạo ra map đẹp lắm, chủ yếu là đường thẳng

