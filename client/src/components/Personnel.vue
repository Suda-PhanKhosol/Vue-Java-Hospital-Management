<template >

  <v-container >
  <v-card
 max-width="800"
    class="mx-auto"
  >
       <div
         v-if="saveStatus.Fail" style="border: 1px solid #FFA879; border-radius: 5px; background-color: #FFD6B2; align-items: center">
          <div style="padding: 15px; color: #733600">{{saveStatus.message}}</div>
        </div>
        <div
         v-if="saveStatus.success" style="border: 1px solid #FFA879; border-radius: 5px; background-color: #FFD6B2; align-items: center">
          <div style="padding: 15px; color: #733600">{{saveStatus.message}}</div>
        </div>

    <v-layout text-center wrap>
      <v-flex >
        <h1 class="display-2 font-weight-bold mb-3">MangePersonnel</h1>
      </v-flex>
    </v-layout>
            
    <v-row justify="center">
      <v-col cols="10">
        <v-form v-model="valid" ref="form" >
          <div>
          <v-row>
            <v-col cols="5">
             <v-text-field
                outlined
                id ="1"
                label="FirstName"
                v-model="personnel.userName"
                  :items="userName"
                  item-text="addname"
                  item-value="addname"
                :rules="[(v) => !!v || 'Item is required']"
                required 
              ><v-icon slot="prepend" color="black" >mdi-account-box</v-icon></v-text-field>
            </v-col>
            <v-col cols="5">
             <v-text-field
                outlined
                id ="2"
                label="LastName"
                v-model="personnel.userlastName"
                  :items="userlastName"
                  item-text="addlastname"
                  item-value="addlastname"
                :rules="[(v) => !!v || 'Item is required']"
                required 
              ></v-text-field>
            </v-col>
          </v-row>
          
          <v-row>
            <v-col cols="10">
             <v-text-field
                id ="3"
                outlined
                label="Telephone"
                v-model="personnel.telephone"
                  item-text="addtelephone"
                  item-value="addtelephone"
                :rules="[(v) => !!v || 'Item is required']"
                required 
              ><v-icon slot="prepend" color="black">mdi-cellphone-android</v-icon></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="10">
             <v-text-field
                id ="4"
                outlined
                label="Password"
                v-model="personnel.password"
                  item-text="addpassword"
                  item-value="addpassword"
                  type = "password"
                :rules="[(v) => !!v || 'Item is required']"
                required 
              ><v-icon slot="prepend" color="black">mdi-key-variant</v-icon></v-text-field>
            </v-col>
          </v-row>
            <v-row >
             <v-col cols="10">
                <v-select
                  label="Maritalstatus"
                  id ="25"
                  outlined
                  v-model="personnel.maritalstatusID"
                  :items="maritalstatuss"
                  item-text="maritalstatus_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-account-heart</v-icon></v-select>
              </v-col>


            </v-row>
            <v-row >
              <v-col cols="10">
                <v-select
                  label="Position"
                  id ="26"
                  outlined
                  v-model="personnel.positionID"
                  :items="positions"
                  item-text="position_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-book</v-icon></v-select>
              </v-col>
            </v-row>
            <v-row>
               <v-col cols="10">
                <v-select
                  id ="27"
                  label="Educationlevel"
                  outlined
                  v-model="personnel.educationlevelID"
                  :items="educationlevels"
                  item-text="educationlevel_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-school</v-icon></v-select>
              </v-col>
            </v-row>
              <v-menu
        v-model="addbirthday"
        :close-on-content-click="false"
        :nudge-right="30"
        transition="scale-transition"
        offset-y
        min-width="280px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
            label="BIRTHDAY"
            readonly
            v-on="on"
            :rules="[(v) => !!v || '*จำเป็น']"
                  required
          ><v-icon slot="prepend" color="black" >mdi-cake-variant</v-icon></v-text-field>
        </template>
        <v-date-picker id ="24" v-model="date" @input="addbirthday = false" format="YYYY-MM-dd"></v-date-picker>
      </v-menu>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn color="red" style="margin-left: 15px;" @click="savePersonnel">submit</v-btn>
                <v-btn color="blue " style="margin-left: 15px; " @click="clear">clear</v-btn>
                <b-button style="margin-left: 15px;">
                <router-link to="/viewpersonnel"><v-btn color="green">SHOWPERSONNEL</v-btn></router-link>
                <flash-message class="savePersonnel"></flash-message>
        </b-button>
              </v-col>
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
    </v-card>
  </v-container>
</template>
          },
<script>
import http from "../http-common";

export default {
  name: "personnel",
  data() {
    return {
      personnel: {
        userName: "",
        userlastName: "",
        addtelephone: "",
        addpassword: "",
        addbirthday: "",
        positionID: "",
        maritalstatusID: "",
        educationlevelID: ""
      },
      userName:[],
      userlastName:[],
      positions:[],
      maritalstatuss:[],
      educationlevels:[],
      valid: false,

       saveStatus: {
        success: false,
        Fail: false,
        clear: false,
        message: ""
      }
    };
  },
  methods: {
    /* eslint-disable no-console */
    getMaritalstatuss() {
      http
        .get("/maritalstatus")
        .then(response => {
          this.maritalstatuss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPositions() {
      http
        .get("/position")
        .then(response => {
          this.positions = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEducationlevels() {
      http
        .get("/educationlevel")
        .then(response => {
          this.educationlevels = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    savePersonnel() {
      http
        .post(
          "/personnel/"+
            this.personnel.userName +
            "/" +
            this.personnel.userlastName +
            "/" +
            this.personnel.telephone +
            "/" +
            this.personnel.password +
            "/" +
            this.date +
            "/"+
            this.personnel.maritalstatusID +
            "/"+
            this.personnel.positionID +
            "/" +
            this.personnel.educationlevelID,
          this.personnel
          
        )
        .then(response => {
          if (response) {
            this.saveStatus.success = true
            this.saveStatus.message = "บันทึกข้อมูลสำเร็จ"
            setTimeout(this.saveStatus.Fail = true,
                       this.saveStatus.Fail =""  ,() => {
                    }, )
          } else {
            this.saveStatus.Fail = true
          }
        })
        .catch(() => {
          this.saveStatus.message = "บันทึกข้อมูลไม่สำเร็จ"
          this.saveStatus.Fail = true
           setTimeout(this.saveStatus.success = true,
           this.saveStatus.success ="" ,() => {
                    },)
        })
         this.$refs.form.reset();
    },
    clear() {
      this.$refs.form.reset();
      this.saveStatus.clear = false
      
    },
    refreshList() {
      this.getMaritalstatuss();
      this.getPositions();
      this.getEducationlevels();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getMaritalstatuss();
    this.getPositions();
    this.getEducationlevels();
  }
};
</script>