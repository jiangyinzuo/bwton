# -*- coding: utf-8 -*-

import os
from io import BytesIO

import oss2
from PIL import Image
from dotenv import load_dotenv, find_dotenv
from oss2.models import GetObjectResult


class OssClient:
    """
    OSS存储客户端
    """
    load_dotenv(find_dotenv())

    def __init__(self):
        self.auth = oss2.Auth(os.environ.get('access_key_id'), os.environ.get('access_key_secret'))
        self.bucket = oss2.Bucket(self.auth, os.environ.get('oss_endpoint'), os.environ.get('bucket_name'))

    def upload_object(self, key: str, data: BytesIO):
        """
        上传对象
        """
        self.bucket.put_object(key, data.getvalue())

    def download_object(self, key: str) -> Image.Image:
        """
        下载对象
        :param key: 文件名称
        :return: 文件字节
        """
        object_stream: GetObjectResult = self.bucket.get_object(key)

        return Image.open(BytesIO(object_stream.read()))
