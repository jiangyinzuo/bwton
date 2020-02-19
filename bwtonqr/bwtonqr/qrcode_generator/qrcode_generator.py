# -*- coding: utf-8 -*-

"""二维码生成器"""
import os
import qrcode
from dotenv import load_dotenv, find_dotenv
from qrcode.image.pil import PilImage


class QRCodeGenerator(object):
    """二维码生成器类"""

    load_dotenv(find_dotenv())
    __request_url = os.environ.get('request_url')

    def __init__(self):
        self.__code_maker = qrcode.QRCode(version=2, box_size=8, border=1)

    def make(self, user_id: int) -> bytes:
        """制作二维码并保存"""
        self.__code_maker.add_data(self.__request_url + str(user_id))
        self.__code_maker.make()
        img: PilImage = self.__code_maker.make_image(fill_color="black", back_color="white")
        return img.get_image().tobytes()


if __name__ == '__main__':
    qr_code_generator = QRCodeGenerator()
    qr_code_generator.make(123)
