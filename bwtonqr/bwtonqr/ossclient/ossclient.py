# -*- coding: utf-8 -*-

import os

import oss2
from dotenv import load_dotenv, find_dotenv


class OssClient(object):
    """
    OSS存储客户端
    """
    def __init__(self):
        self.auth = oss2.Auth(os.environ.get('access_key_id'), os.environ.get('access_key_secret'))
        self.bucket = oss2.Bucket(self.auth, os.environ.get('oss_endpoint'), os.environ.get('bucket_name'))

    def upload_file(self):
        """
        上传文件
        """
        self.bucket.put_object_from_file('avatar/ttt', '../../Dockerfile')


if __name__ == '__main__':
    load_dotenv(find_dotenv())
    client = OssClient()
    client.upload_file()
