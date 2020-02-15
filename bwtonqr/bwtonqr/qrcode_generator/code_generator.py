# -*- coding: utf-8 -*-

"""二维码生成器"""

import qrcode


class QRCodeGenerator(object):
    """二维码生成器类"""

    @staticmethod
    def make() -> None:
        """制作二维码并保存"""
        img = qrcode.make({"id": 123, "nickname": "李白🐟"})
        with open('test.png', 'wb') as f:
            img.save(f)


def make_qr_code(content, save_path=None):
    """
    Generate QR Code by given params
    :param content: The content encoded in QR Code
    :param save_path: The path where the generated QR Code image will be saved in.
                      If the path is not given the image will be opened by default.
    """
    qr_code_maker = qrcode.QRCode(version=2, box_size=8, border=1)
    qr_code_maker.add_data(data=content)
    qr_code_maker.make()
    img = qr_code_maker.make_image(fill_color="black", back_color="white")
    if save_path:
        img.save(save_path)
    else:
        img.show()


if __name__ == '__main__':
    QRCodeGenerator.make()
