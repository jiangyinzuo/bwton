# -*- coding: utf-8 -*-

"""二维码生成器"""
import os
from typing import Final
from io import BytesIO

import qrcode
from PIL.Image import Image, ANTIALIAS
from dotenv import load_dotenv, find_dotenv
from qrcode.image.pil import PilImage


def gen_qr_code_with_avatar(qr_code: PilImage, avatar: Image) -> BytesIO:
    """
    生成中间带头像的二维码
    :return: 图片字节
    """
    qr_code_size_x, qr_code_size_y = qr_code.size
    avatar = avatar.resize((qr_code_size_x // 3, qr_code_size_y // 3), ANTIALIAS)
    qr_code.paste(avatar, (qr_code_size_x // 3, qr_code_size_y // 3))
    data = BytesIO()
    qr_code.save(data)
    return data


class QRCodeGenerator:
    """二维码生成器类"""

    load_dotenv(find_dotenv())
    __REQUEST_URL: Final[str] = os.environ.get('request_url')
    if __REQUEST_URL is None:
        raise FileNotFoundError("找不到配置项`request_url`")

    def __init__(self):
        self.__code_maker = qrcode.QRCode(version=2, error_correction=qrcode.ERROR_CORRECT_H, box_size=8)

    def make(self, user_id: int) -> PilImage:
        """制作二维码并保存"""
        self.__code_maker.add_data(self.__REQUEST_URL + str(user_id))
        self.__code_maker.make()
        img: PilImage = self.__code_maker.make_image(fill_color='#000000', back_color="white")
        return img


if __name__ == '__main__':
    qr_code_generator = QRCodeGenerator()
    print(type(qr_code_generator.make(123)))
