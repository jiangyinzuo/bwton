# -*- coding: utf-8 -*-
from io import BytesIO

from PIL import Image
from qrcode.image.pil import PilImage

from bwtonqr.ossclient.ossclient import OssClient
from bwtonqr.qrcode_generator.qrcode_generator import QRCodeGenerator


class TestOssClient:

    oss_client = OssClient()
    qr_code_generator = QRCodeGenerator()

    def test_upload_file(self):

        img: PilImage = self.qr_code_generator.make(123)
        data = BytesIO()
        img.save(data)
        self.oss_client.upload_object('qrcode/123.png', data)

    def test_download_file(self):
        img: Image.Image = self.oss_client.download_object('avatar/456.png')
        img.save('test2.png')
