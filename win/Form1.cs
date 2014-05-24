using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AndroidCopyPaster
{
    public partial class Form1 : Form
    {
		//SET YOU SERVER!
        //private string url = "http://cloud-paster.com/";
        private string filePathSend = "send.php";
        private string filePathTry = "try.php";

        Dictionary<string,string> stringsEn=new Dictionary<string,string>(){
            {"errorTitle","Error"},
            {"errorLogin","Error in login or password."},
            {"successTitle","Success"},
            {"successSave","Config saved!"},
            {"buttonSave.Text","Save password and configuration"},
            {"label1.Text","Login"},
            {"label2.Text","Password"},
            {"label3.Text","Key combination"},
            {"checkBox1.Text","Show notifications"},
            {"WRONGLOGIN","Wrong login or password!"},
            {"TOLONG","Too long text in clipboard."},
            {"OK!","Text sended!"},
        };

        Dictionary<string, string> stringsRu = new Dictionary<string, string>(){
            {"errorTitle","Ошибка"},
            {"errorLogin","Неверный логин или пароль."},
            {"successTitle","Успех"},
            {"successSave","Настройки сохранены!"},
            {"buttonSave.Text","Сохранить пароль и настройки."},
            {"label1.Text","Логин"},
            {"label2.Text","Пароль"},
            {"label3.Text","Комбинация клавиш"},
            {"checkBox1.Text","Показывать уведомления"},      
            {"WRONGLOGIN","Неверный логин или пароль."},
            {"TOLONG","Слишком длинный текст в буфере обмена."},
            {"OK!","Текст отправлен!"},
        };

        String lang = "en";

        private String GetString(string name)
        {
            if (lang == "en")
            {
                return stringsEn[name];
            }
            return stringsRu[name];
        }

        public Form1()
        {
            InitializeComponent();
            buttonSave.Text = GetString("buttonSave.Text");
            label1.Text = GetString("label1.Text");
            label2.Text = GetString("label2.Text");
            label4.Text = GetString("label3.Text");
            checkBox1.Text = GetString("checkBox1.Text");
        }

        private void GlobalKeyUp(object sender, KeyEventArgs e)
        {
            mouseKeyEventProvider.Enabled = false;

            bool Shift = ((e.Modifiers & Keys.Shift) == Keys.Shift);
            bool Alt = ((e.Modifiers & Keys.Alt) == Keys.Alt);
            bool Ctrl = ((e.Modifiers & Keys.Control) == Keys.Control);

            if (Convert.ToString(e.KeyCode).Equals(Convert.ToString(Properties.Settings.Default.Key))
                && Shift == Properties.Settings.Default.Shift
                && Alt == Properties.Settings.Default.Alt
                && Ctrl == Properties.Settings.Default.Ctrl)
            {
                String text = Clipboard.GetText();

                if (text.Length >= 4000)
                {
                    notifyIconMain.BalloonTipText = GetString("TOLONG");
                    notifyIconMain.ShowBalloonTip(5);
                }
                else
                {
                    using (var client = new HttpClient())
                    {
                        client.BaseAddress = new Uri(url);
                        var content = new FormUrlEncodedContent(new[] 
                        {
                            new KeyValuePair<string, string>("login", Properties.Settings.Default.Login),
                            new KeyValuePair<string, string>("pass", Properties.Settings.Default.Pass),
                            new KeyValuePair<string, string>("message", text)
                        });
                        var result = client.PostAsync(filePathSend, content).Result;
                        string resultContent = result.Content.ReadAsStringAsync().Result;
                        if (Properties.Settings.Default.ShowBallon)
                        {
                            String message = resultContent;

                            if (resultContent.Contains("WRONGLOGIN"))
                            {
                                message = GetString("WRONGLOGIN");
                            }
                            if (resultContent.Contains("TOLONG"))
                            {
                                message = GetString("TOLONG");
                            }
                            if (resultContent.Contains("OK!"))
                            {
                                message = GetString("OK!");
                            }

                            notifyIconMain.BalloonTipText = message;
                            notifyIconMain.ShowBalloonTip(5);
                        }
                    }
                }

            }

            mouseKeyEventProvider.Enabled = true;
        }

        protected override void OnResize(EventArgs e)
        {
            if (this.WindowState == FormWindowState.Minimized)
            {
                Minimize();
            }
        }

        private void IconClick(object sender, EventArgs e)
        {
            if (this.WindowState == FormWindowState.Minimized)
            {
                this.Show();
                WindowState = FormWindowState.Normal;
                this.ShowInTaskbar = true;
            }
            else
            {
                Minimize();
            }

        }

        private void Minimize()
        {
            this.ShowInTaskbar = false;
            this.Hide();
            this.WindowState = FormWindowState.Minimized;
        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            Properties.Settings.Default.Save();
            progressBar.Visible = true;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(url);
                var content = new FormUrlEncodedContent(new[] 
                        {
                            new KeyValuePair<string, string>("login", Properties.Settings.Default.Login),
                            new KeyValuePair<string, string>("pass", Properties.Settings.Default.Pass),
                        });
                var result = client.PostAsync(filePathTry, content).Result;
                string resultContent = result.Content.ReadAsStringAsync().Result;

                progressBar.Visible = false;

                if (!resultContent.Contains("OK"))
                {
                    MessageBox.Show(GetString("errorLogin"), GetString("errorTitle"));
                    Properties.Settings.Default.Saved = false;
                }
                else
                {
                    MessageBox.Show(GetString("successSave"), GetString("successTitle"));
                    Properties.Settings.Default.Saved = true;
                }
            }
        }


        private void textBoxKey_KeyUp(object sender, KeyEventArgs e)
        {
            String key=Convert.ToString(e.KeyCode);
            if (key.Length==1){
                textBoxKey.Text = key;
                Properties.Settings.Default.Save();
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}