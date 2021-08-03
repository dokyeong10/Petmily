<template>
  <el-dialog custom-class="signup-dialog" title="회원 가입" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="email" label="이메일" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
      </el-form-item>
       <el-form-item prop="confirmPassword" label="비밀번호 확인" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.confirmPassword" autocomplete="off" show-password></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clickSignup">회원 가입</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.signup-dialog {
  width: 400px !important;
  height: 600px;
}
.signup-dialog .el-dialog__headerbtn {
  float: right;
}
.signup-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.signup-dialog .el-form-item {
  margin-bottom: 30px;
}
.signup-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.signup-dialog .el-input__suffix {
  display: none;
}
.signup-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.signup-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'signup-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)

    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    let reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-])/
    const validatePassword = (rule, value, callback) => {
        if (!reg.test(value)) {
            callback(new Error('Should contain at least one number, letter and special character.'))
        } else {
            callback()
        }
    }
    const validateConfirmPassword = (rule, value, callback) => {
        // vue 2 버전에서는 this.form.password 였는데 3 버전이라 state.form.password 이다
        if (value === state.form.password) {
            callback()
        } else {
            callback(new Error('The password does not match.'))
        }
    }
    const state = reactive({
      form: {
        email: '',
        name: '',
        password: '',
        confirmPassword: '',
        align: 'left'
      },
      rules: {
        email: [
          { required: true, message: 'Please input Email', trigger: 'blur' }
        ],
        name: [
          { required: true, message: 'Please input your name', trigger: 'blur' },
          { min: 0, max: 30, message: 'Length of 0 to 30 characters', trigger: 'blur' },
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          { min: 9, max: 16, message: 'Length of 9 to 16 characters', trigger: 'blur' },
          { validator: validatePassword, trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          { min: 9, max: 16, message: 'Length of 9 to 16 characters', trigger: 'blur' },
          { validator: validateConfirmPassword, trigger: 'blur' }
        ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(signupForm.value)
    })

    const clickSignup = function () {
      // 회원가입 클릭 시 validate 체크 후 그 결과 값에 따라, API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          console.log('submit')
          store.dispatch('root/requestSignup', { team: state.form.team, position: state.form.position, name: state.form.name, id: state.form.id, password: state.form.password, confirmPassword: state.form.confirmPassword })
          .then(function (result) {
            alert('accessToken: ' + result.data.accessToken)
            // localStorage에 accessToken 추가
            localStorage.setItem('accessToken', result.data.accessToken)
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const handleClose = function () {
      state.form.id = ''
      state.form.password = ''
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, handleClose }
  }
}
</script>
