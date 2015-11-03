/* 
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <jni.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <grp.h>
#include <pwd.h>
#include <unistd.h>

jboolean com_android_cts_verifier_os_FileUtils_getFileStatus(JNIEnv* env, jobject thiz,
        jstring path)
{
    const char* pathStr = env->GetStringUTFChars(path, NULL);
    jboolean ret = false;

	if (access(pathStr, X_OK) == 0) {
	    ret = true;
	} 
    env->ReleaseStringUTFChars(path, pathStr);

    return ret;
}

static JNINativeMethod gMethods[] = {
    {  "checkFileExec", "(Ljava/lang/String;)Z",
            (void *) com_android_cts_verifier_os_FileUtils_getFileStatus  },
};

int register_com_android_cts_verifier_os_FileUtils(JNIEnv* env)
{
    jclass clazz = env->FindClass("com/android/mytest/FileUtils");

    return env->RegisterNatives(clazz, gMethods, 
            sizeof(gMethods) / sizeof(JNINativeMethod)); 
}
