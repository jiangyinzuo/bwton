# -*- coding: utf-8 -*-
"""
二维码生成系统
"""
from bwtonqr.ossclient.ossclient import OssClient
from bwtonqr.qrcode_generator.qrcode_generator import QRCodeGenerator


class Application(object):
    """
    入口类
    """

    def __init__(self):
        self.__oss_client = OssClient()
        self.__qr_code_generator = QRCodeGenerator()

    def run(self) -> None:
        """
        启动
        :return: None
        """
        pass


if __name__ == '__main__':
    app = Application()
