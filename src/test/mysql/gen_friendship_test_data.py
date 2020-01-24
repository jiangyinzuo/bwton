from random import randint


with open('friendship_test_data.txt', 'w') as f:
    f.write('user1_id, user2_id\n')
    count = 0
    data_set = set()
    while count < 1000000:
        temp_num1 = randint(3000, 6000)
        temp_num2 = randint(3000, 6000)
        pair = (None, None)
        while pair in data_set or temp_num1 == temp_num2:
            pair = (temp_num1, temp_num2) if temp_num1 > temp_num2 else (temp_num2, temp_num1)
            temp_num2 = randint(3000, 6000)
        data_set.add((temp_num1, temp_num2))
        count += 1

    for data in data_set:
        f.write("{}, {}\n".format(data[0], data[1]))
