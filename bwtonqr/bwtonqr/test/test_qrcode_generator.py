# -*- coding: utf-8 -*-
from PIL.Image import Image
from qrcode.image.pil import PilImage

from bwtonqr.ossclient.ossclient import OssClient
from bwtonqr.qrcode_generator.qrcode_generator import QRCodeGenerator, gen_qr_code_with_avatar


def test_gen_qr_code_with_avatar():
    qr_code_generator = QRCodeGenerator()
    oss_client = OssClient()
    img: PilImage = qr_code_generator.make(123)
    avatar: Image = oss_client.download_object('avatar/avatar.png')
    oss_client.upload_object('qrcode/123.png', gen_qr_code_with_avatar(img, avatar))
